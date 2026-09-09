package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlAssessmentChecklist {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, asmd_keyid, topi_keyid, chek_keyid, status, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlAssessmentChecklist()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getAsclKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAsclKeyid(String asclKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = asclKeyid;
	}

	public String getAsclAsmdKeyid() {
		return (String) saveArray[ tableFldConstants.asmd_keyid.ordinal() ];
	}

	public void setAsclAsmdKeyid(String asclAsmdKeyid) {
		saveArray[ tableFldConstants.asmd_keyid.ordinal() ] = asclAsmdKeyid;
	}

	public String getAsclTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setAsclTopiKeyid(String asclTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = asclTopiKeyid;
	}

	public String getAsclChekKeyid() {
		return (String) saveArray[ tableFldConstants.chek_keyid.ordinal() ];
	}

	public void setAsclChekKeyid(String asclChekKeyid) {
		saveArray[ tableFldConstants.chek_keyid.ordinal() ] = asclChekKeyid;
	}

	public String getAsclStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setAsclStatus(String asclStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = asclStatus;
	}

	public String getAsclTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAsclTempfield1(String asclTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = asclTempfield1;
	}

	public String getAsclTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAsclTempfield2(String asclTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = asclTempfield2;
	}

	public String getAsclTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAsclTempfield3(String asclTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = asclTempfield3;
	}

	public String getAsclTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAsclTempfield4(String asclTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = asclTempfield4;
	}

	public String getAsclTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAsclTempfield5(String asclTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = asclTempfield5;
	}

	public String getAsclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAsclActive(String asclActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = asclActive;
	}

	public String getAsclCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAsclCreatedby(String asclCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = asclCreatedby;
	}

	public String getAsclCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAsclCreatedon(String asclCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = asclCreatedon;
	}

	public String getAsclModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAsclModifiedon(String asclModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = asclModifiedon;
	}

}

