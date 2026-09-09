package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedbackRequestdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, frqm_keyid, empm_keyid, ismailsent, status, feedback_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlFeedbackRequestdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFrqdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFrqdKeyid(String frqdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = frqdKeyid;
	}

	public String getFrqdFrqmKeyid() {
		return (String) saveArray[ tableFldConstants.frqm_keyid.ordinal() ];
	}

	public void setFrqdFrqmKeyid(String frqdFrqmKeyid) {
		saveArray[ tableFldConstants.frqm_keyid.ordinal() ] = frqdFrqmKeyid;
	}

	public String getFrqdEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setFrqdEmpmKeyid(String frqdEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = frqdEmpmKeyid;
	}

	public String getFrqdIsmailsent() {
		return (String) saveArray[ tableFldConstants.ismailsent.ordinal() ];
	}

	public void setFrqdIsmailsent(String frqdIsmailsent) {
		saveArray[ tableFldConstants.ismailsent.ordinal() ] = frqdIsmailsent;
	}

	public String getFrqdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setFrqdStatus(String frqdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = frqdStatus;
	}

	public String getFrqdFeedbackDate() {
		return (String) saveArray[ tableFldConstants.feedback_date.ordinal() ];
	}

	public void setFrqdFeedbackDate(String frqdFeedbackDate) {
		saveArray[ tableFldConstants.feedback_date.ordinal() ] = frqdFeedbackDate;
	}

	public String getFrqdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFrqdTempfield1(String frqdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = frqdTempfield1;
	}

	public String getFrqdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFrqdTempfield2(String frqdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = frqdTempfield2;
	}

	public String getFrqdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFrqdTempfield3(String frqdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = frqdTempfield3;
	}

	public String getFrqdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFrqdTempfield4(String frqdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = frqdTempfield4;
	}

	public String getFrqdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFrqdTempfield5(String frqdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = frqdTempfield5;
	}

	public String getFrqdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFrqdActive(String frqdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = frqdActive;
	}

	public String getFrqdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFrqdCreatedby(String frqdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = frqdCreatedby;
	}

	public String getFrqdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFrqdCreatedon(String frqdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = frqdCreatedon;
	}

	public String getFrqdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFrqdModifiedon(String frqdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = frqdModifiedon;
	}

}

