package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleSkillRating {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, ersl_keyid, erdl_keyid, skil_keyid, skrm_keyid, sklm_keyid
		, minpoints, eff_from_date, eff_till_date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlRoleSkillRating()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getErsrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setErsrKeyid(String ersrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ersrKeyid;
	}

	public String getErsrErslKeyid() {
		return (String) saveArray[ tableFldConstants.ersl_keyid.ordinal() ];
	}

	public void setErsrErslKeyid(String ersrErslKeyid) {
		saveArray[ tableFldConstants.ersl_keyid.ordinal() ] = ersrErslKeyid;
	}

	public String getErsrErdlKeyid() {
		return (String) saveArray[ tableFldConstants.erdl_keyid.ordinal() ];
	}

	public void setErsrErdlKeyid(String ersrErdlKeyid) {
		saveArray[ tableFldConstants.erdl_keyid.ordinal() ] = ersrErdlKeyid;
	}

	public String getErsrSkilKeyid() {
		return (String) saveArray[ tableFldConstants.skil_keyid.ordinal() ];
	}

	public void setErsrSkilKeyid(String ersrSkilKeyid) {
		saveArray[ tableFldConstants.skil_keyid.ordinal() ] = ersrSkilKeyid;
	}

	public String getErsrSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setErsrSkrmKeyid(String ersrSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = ersrSkrmKeyid;
	}

	public String getErsrSklmKeyid() {
		return (String) saveArray[ tableFldConstants.sklm_keyid.ordinal() ];
	}

	public void setErsrSklmKeyid(String ersrSklmKeyid) {
		saveArray[ tableFldConstants.sklm_keyid.ordinal() ] = ersrSklmKeyid;
	}

	public String getErsrMinpoints() {
		return (String) saveArray[ tableFldConstants.minpoints.ordinal() ];
	}

	public void setErsrMinpoints(String ersrMinpoints) {
		saveArray[ tableFldConstants.minpoints.ordinal() ] = ersrMinpoints;
	}

	public String getErsrEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setErsrEffFromDate(String ersrEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = ersrEffFromDate;
	}

	public String getErsrEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setErsrEffTillDate(String ersrEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = ersrEffTillDate;
	}

	public String getErsrTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setErsrTempfield1(String ersrTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = ersrTempfield1;
	}

	public String getErsrTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setErsrTempfield2(String ersrTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ersrTempfield2;
	}

	public String getErsrTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setErsrTempfield3(String ersrTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ersrTempfield3;
	}

	public String getErsrTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setErsrTempfield4(String ersrTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = ersrTempfield4;
	}

	public String getErsrTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setErsrTempfield5(String ersrTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = ersrTempfield5;
	}

	public String getErsrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setErsrActive(String ersrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ersrActive;
	}

	public String getErsrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setErsrCreatedby(String ersrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ersrCreatedby;
	}

	public String getErsrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setErsrCreatedon(String ersrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ersrCreatedon;
	}

	public String getErsrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setErsrModifiedon(String ersrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ersrModifiedon;
	}

}

