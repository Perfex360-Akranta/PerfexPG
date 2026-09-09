package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlFiveSAudit {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, fvas_keyid, entrydate, responsibilityid, frequency, date
		, value, status, circleid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public JhaTlFiveSAudit()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFvadKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFvadKeyid(String fvadKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fvadKeyid;
	}

	public String getFvadFvasKeyid() {
		return (String) saveArray[ tableFldConstants.fvas_keyid.ordinal() ];
	}

	public void setFvadFvasKeyid(String fvadFvasKeyid) {
		saveArray[ tableFldConstants.fvas_keyid.ordinal() ] = fvadFvasKeyid;
	}

	public String getFvadEntrydate() {
		return (String) saveArray[ tableFldConstants.entrydate.ordinal() ];
	}

	public void setFvadEntrydate(String fvadEntrydate) {
		saveArray[ tableFldConstants.entrydate.ordinal() ] = fvadEntrydate;
	}

	public String getFvadResponsibilityid() {
		return (String) saveArray[ tableFldConstants.responsibilityid.ordinal() ];
	}

	public void setFvadResponsibilityid(String fvadResponsibilityid) {
		saveArray[ tableFldConstants.responsibilityid.ordinal() ] = fvadResponsibilityid;
	}

	public String getFvadFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setFvadFrequency(String fvadFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = fvadFrequency;
	}

	public String getFvadDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setFvadDate(String fvadDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = fvadDate;
	}

	public String getFvadValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setFvadValue(String fvadValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = fvadValue;
	}

	public String getFvadStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setFvadStatus(String fvadStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = fvadStatus;
	}

	public String getFvadCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setFvadCircleid(String fvadCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = fvadCircleid;
	}

	public String getFvadTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFvadTempfield1(String fvadTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fvadTempfield1;
	}

	public String getFvadTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFvadTempfield2(String fvadTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fvadTempfield2;
	}

	public String getFvadTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFvadTempfield3(String fvadTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fvadTempfield3;
	}

	public String getFvadTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFvadTempfield4(String fvadTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fvadTempfield4;
	}

	public String getFvadTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFvadTempfield5(String fvadTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fvadTempfield5;
	}

	public String getFvadActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFvadActive(String fvadActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fvadActive;
	}

	public String getFvadCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFvadCreatedby(String fvadCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fvadCreatedby;
	}

	public String getFvadCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFvadCreatedon(String fvadCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fvadCreatedon;
	}

	public String getFvadModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFvadModifiedon(String fvadModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fvadModifiedon;
	}

}

