package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlJhactivitychartmst {

	private  Object [] saveArray = null;  
	private List<GenTlJhactivitychartdtl> genTlJhactivitychartdtl;

	public enum   tableFldConstants
	{
		keyid, activity, frequency, effectivedate, inactivedate, flid
		, roleid, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public GenTlJhactivitychartmst()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAchmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAchmKeyid(String achmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = achmKeyid;
	}

	public String getAchmActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setAchmActivity(String achmActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = achmActivity;
	}

	public String getAchmFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setAchmFrequency(String achmFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = achmFrequency;
	}

	public String getAchmEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setAchmEffectivedate(String achmEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = achmEffectivedate;
	}

	public String getAchmInactivedate() {
		return (String) saveArray[ tableFldConstants.inactivedate.ordinal() ];
	}

	public void setAchmInactivedate(String achmInactivedate) {
		saveArray[ tableFldConstants.inactivedate.ordinal() ] = achmInactivedate;
	}

	public String getAchmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setAchmFlid(String achmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = achmFlid;
	}

	public String getAchmRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setAchmRoleid(String achmRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = achmRoleid;
	}

	public String getAchmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAchmTempfield1(String achmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = achmTempfield1;
	}

	public String getAchmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAchmTempfield2(String achmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = achmTempfield2;
	}

	public String getAchmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAchmTempfield3(String achmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = achmTempfield3;
	}

	public String getAchmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAchmTempfield4(String achmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = achmTempfield4;
	}

	public String getAchmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAchmTempfield5(String achmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = achmTempfield5;
	}

	public String getAchmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAchmActive(String achmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = achmActive;
	}

	public String getAchmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAchmCreatedby(String achmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = achmCreatedby;
	}

	public String getAchmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAchmCreatedon(String achmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = achmCreatedon;
	}

	public String getAchmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAchmModifiedon(String achmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = achmModifiedon;
	}
	public void setGenTlJhactivitychartdtl(List<GenTlJhactivitychartdtl> genTlJhactivitychartdtl) {
		this.genTlJhactivitychartdtl = genTlJhactivitychartdtl;
	}

	public List<GenTlJhactivitychartdtl> getGenTlJhactivitychartdtl() {
		return genTlJhactivitychartdtl;
	}

	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
}

