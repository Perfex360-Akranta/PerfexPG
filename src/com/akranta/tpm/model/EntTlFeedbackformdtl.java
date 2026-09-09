package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedbackformdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tmst_keyid, fbpm_keyid, ferm_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlFeedbackformdtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFbfdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFbfdKeyid(String fbfdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fbfdKeyid;
	}

	public String getFbfdTmstKeyid() {
		return (String) saveArray[ tableFldConstants.tmst_keyid.ordinal() ];
	}

	public void setFbfdTmstKeyid(String fbfdTmstKeyid) {
		saveArray[ tableFldConstants.tmst_keyid.ordinal() ] = fbfdTmstKeyid;
	}

	public String getFbfdFbpmKeyid() {
		return (String) saveArray[ tableFldConstants.fbpm_keyid.ordinal() ];
	}

	public void setFbfdFbpmKeyid(String fbfdFbpmKeyid) {
		saveArray[ tableFldConstants.fbpm_keyid.ordinal() ] = fbfdFbpmKeyid;
	}

	public String getFbfdFermKeyid() {
		return (String) saveArray[ tableFldConstants.ferm_keyid.ordinal() ];
	}

	public void setFbfdFermKeyid(String fbfdFermKeyid) {
		saveArray[ tableFldConstants.ferm_keyid.ordinal() ] = fbfdFermKeyid;
	}

	public String getFbfdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFbfdTempfield1(String fbfdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fbfdTempfield1;
	}

	public String getFbfdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFbfdTempfield2(String fbfdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fbfdTempfield2;
	}

	public String getFbfdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFbfdTempfield3(String fbfdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fbfdTempfield3;
	}

	public String getFbfdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFbfdTempfield4(String fbfdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fbfdTempfield4;
	}

	public String getFbfdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFbfdTempfield5(String fbfdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fbfdTempfield5;
	}

	public String getFbfdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFbfdActive(String fbfdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fbfdActive;
	}

	public String getFbfdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFbfdCreatedby(String fbfdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fbfdCreatedby;
	}

	public String getFbfdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFbfdCreatedon(String fbfdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fbfdCreatedon;
	}

	public String getFbfdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFbfdModifiedon(String fbfdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fbfdModifiedon;
	}

}

