package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSkillRatingmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, sklm_keyid, orderno, description, minpoints, maxpoints
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlSkillRatingmst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSkrmKeyid(String skrmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = skrmKeyid;
	}

	public String getSkrmSklmKeyid() {
		return (String) saveArray[ tableFldConstants.sklm_keyid.ordinal() ];
	}

	public void setSkrmSklmKeyid(String skrmSklmKeyid) {
		saveArray[ tableFldConstants.sklm_keyid.ordinal() ] = skrmSklmKeyid;
	}

	public String getSkrmOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setSkrmOrderno(String skrmOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = skrmOrderno;
	}

	public String getSkrmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setSkrmDescription(String skrmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = skrmDescription;
	}

	public String getSkrmMinpoints() {
		return (String) saveArray[ tableFldConstants.minpoints.ordinal() ];
	}

	public void setSkrmMinpoints(String skrmMinpoints) {
		saveArray[ tableFldConstants.minpoints.ordinal() ] = skrmMinpoints;
	}

	public String getSkrmMaxpoints() {
		return (String) saveArray[ tableFldConstants.maxpoints.ordinal() ];
	}

	public void setSkrmMaxpoints(String skrmMaxpoints) {
		saveArray[ tableFldConstants.maxpoints.ordinal() ] = skrmMaxpoints;
	}

	public String getSkrmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSkrmTempfield1(String skrmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = skrmTempfield1;
	}

	public String getSkrmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSkrmTempfield2(String skrmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = skrmTempfield2;
	}

	public String getSkrmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSkrmTempfield3(String skrmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = skrmTempfield3;
	}

	public String getSkrmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSkrmTempfield4(String skrmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = skrmTempfield4;
	}

	public String getSkrmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSkrmTempfield5(String skrmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = skrmTempfield5;
	}

	public String getSkrmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSkrmActive(String skrmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = skrmActive;
	}

	public String getSkrmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSkrmCreatedby(String skrmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = skrmCreatedby;
	}

	public String getSkrmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSkrmCreatedon(String skrmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = skrmCreatedon;
	}

	public String getSkrmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSkrmModifiedon(String skrmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = skrmModifiedon;
	}

}

