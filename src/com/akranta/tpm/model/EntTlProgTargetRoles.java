package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlProgTargetRoles {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, prog_keyid,trar_keyid, eff_from_date, eff_till_date, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlProgTargetRoles()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getPrtrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrtrKeyid(String prtrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prtrKeyid;
	}

	public String getPrtrProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setPrtrProgKeyid(String prtrProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = prtrProgKeyid;
	}

	public String getPrtrTrarKeyid() {
		return (String) saveArray[ tableFldConstants.trar_keyid.ordinal() ];
	}

	public void setPrtrTrarKeyid(String prtrTrarKeyid) {
		saveArray[ tableFldConstants.trar_keyid.ordinal() ] = prtrTrarKeyid;
	}

	public String getPrtrEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setPrtrEffFromDate(String prtrEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = prtrEffFromDate;
	}

	public String getPrtrEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setPrtrEffTillDate(String prtrEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = prtrEffTillDate;
	}

	public String getPrtrTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPrtrTempfield1(String prtrTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = prtrTempfield1;
	}

	public String getPrtrTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPrtrTempfield2(String prtrTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = prtrTempfield2;
	}

	public String getPrtrTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPrtrTempfield3(String prtrTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = prtrTempfield3;
	}

	public String getPrtrTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPrtrTempfield4(String prtrTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = prtrTempfield4;
	}

	public String getPrtrTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPrtrTempfield5(String prtrTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = prtrTempfield5;
	}

	public String getPrtrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrtrActive(String prtrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prtrActive;
	}

	public String getPrtrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrtrCreatedby(String prtrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prtrCreatedby;
	}

	public String getPrtrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrtrCreatedon(String prtrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prtrCreatedon;
	}

	public String getPrtrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrtrModifiedon(String prtrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prtrModifiedon;
	}

}

