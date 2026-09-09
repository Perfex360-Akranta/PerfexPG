package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlProgImpactSkills {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, prts_keyid, skrm_keyid, minpoints, eff_from_date, eff_till_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlProgImpactSkills()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPimsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPimsKeyid(String pimsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pimsKeyid;
	}

	public String getPimsPrtsKeyid() {
		return (String) saveArray[ tableFldConstants.prts_keyid.ordinal() ];
	}

	public void setPimsPrtsKeyid(String pimsPrtsKeyid) {
		saveArray[ tableFldConstants.prts_keyid.ordinal() ] = pimsPrtsKeyid;
	}

	public String getPimsSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setPimsSkrmKeyid(String pimsSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = pimsSkrmKeyid;
	}

	public String getPimsMinpoints() {
		return (String) saveArray[ tableFldConstants.minpoints.ordinal() ];
	}

	public void setPimsMinpoints(String pimsMinpoints) {
		saveArray[ tableFldConstants.minpoints.ordinal() ] = pimsMinpoints;
	}

	public String getPimsEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setPimsEffFromDate(String pimsEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = pimsEffFromDate;
	}

	public String getPimsEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setPimsEffTillDate(String pimsEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = pimsEffTillDate;
	}

	public String getPimsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPimsTempfield1(String pimsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pimsTempfield1;
	}

	public String getPimsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPimsTempfield2(String pimsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pimsTempfield2;
	}

	public String getPimsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPimsTempfield3(String pimsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pimsTempfield3;
	}

	public String getPimsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPimsTempfield4(String pimsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pimsTempfield4;
	}

	public String getPimsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPimsTempfield5(String pimsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = pimsTempfield5;
	}

	public String getPimsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPimsActive(String pimsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pimsActive;
	}

	public String getPimsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPimsCreatedby(String pimsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pimsCreatedby;
	}

	public String getPimsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPimsCreatedon(String pimsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pimsCreatedon;
	}

	public String getPimsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPimsModifiedon(String pimsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pimsModifiedon;
	}

	

}

