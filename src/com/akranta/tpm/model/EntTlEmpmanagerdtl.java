package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlEmpmanagerdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, eemm_keyid, empm_keyid, mailid, mobileno, tempfiled1, tempfiled2
		, tempfiled3, active, createdby, createdon, modifiedon
	}

	public EntTlEmpmanagerdtl()
	{
		saveArray = new  Object [ 12 ];
		
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

	public String getEemdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEemdKeyid(String eemdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eemdKeyid;
	}

	public String getEemdEemmKeyid() {
		return (String) saveArray[ tableFldConstants.eemm_keyid.ordinal() ];
	}

	public void setEemdEemmKeyid(String eemdEemmKeyid) {
		saveArray[ tableFldConstants.eemm_keyid.ordinal() ] = eemdEemmKeyid;
	}

	public String getEemdEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setEemdEmpmKeyid(String eemdEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = eemdEmpmKeyid;
	}

	public String getEemdMailid() {
		return (String) saveArray[ tableFldConstants.mailid.ordinal() ];
	}

	public void setEemdMailid(String eemdMailid) {
		saveArray[ tableFldConstants.mailid.ordinal() ] = eemdMailid;
	}

	public String getEemdMobileno() {
		return (String) saveArray[ tableFldConstants.mobileno.ordinal() ];
	}

	public void setEemdMobileno(String eemdMobileno) {
		saveArray[ tableFldConstants.mobileno.ordinal() ] = eemdMobileno;
	}

	public String getEemdTempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setEemdTempfiled1(String eemdTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = eemdTempfiled1;
	}

	public String getEemdTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setEemdTempfiled2(String eemdTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = eemdTempfiled2;
	}

	public String getEemdTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setEemdTempfiled3(String eemdTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = eemdTempfiled3;
	}

	public String getEemdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEemdActive(String eemdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eemdActive;
	}

	public String getEemdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEemdCreatedby(String eemdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eemdCreatedby;
	}

	public String getEemdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEemdCreatedon(String eemdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eemdCreatedon;
	}

	public String getEemdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEemdModifiedon(String eemdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eemdModifiedon;
	}

}

