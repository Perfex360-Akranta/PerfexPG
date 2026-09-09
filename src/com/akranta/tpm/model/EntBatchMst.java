package com.akranta.tpm.model;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonMessage;

public class EntBatchMst {

	private  Object [] saveArray = null;  
	
    private String isbatch;
    private String bachFromTime;
    private String bachTillTime;
	public enum   tableFldConstants
	{
		keyid, code, name, fact_keyid, prog_keyid, venu_keyid, fromdate
		, tilldate, minsize, maxsize, duration, remarks, status, completedate
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntBatchMst()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBachKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBachKeyid(String bachKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bachKeyid;
	}

	public String getBachCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setBachCode(String bachCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = bachCode;
	}

	public String getBachName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setBachName(String bachName) {
		saveArray[ tableFldConstants.name.ordinal() ] = bachName;
	}

	public String getBachFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setBachFactKeyid(String bachFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = bachFactKeyid;
	}

	public String getBachProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setBachProgKeyid(String bachProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = bachProgKeyid;
	}

	public String getBachVenuKeyid() {
		return (String) saveArray[ tableFldConstants.venu_keyid.ordinal() ];
	}

	public void setBachVenuKeyid(String bachVenuKeyid) {
		saveArray[ tableFldConstants.venu_keyid.ordinal() ] = bachVenuKeyid;
	}

	public String getBachFromdate() {
		return UIUtils.getActualDateForm((String) saveArray[ tableFldConstants.fromdate.ordinal() ]);
	}

	public void setBachFromdate(String bachFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = bachFromdate;
	}

	public String getBachTilldate() {
		return UIUtils.getActualDateForm((String) saveArray[ tableFldConstants.tilldate.ordinal() ]);
	}

	public void setBachTilldate(String bachTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = bachTilldate;
	}

	public String getBachMinsize() {
		return (String) saveArray[ tableFldConstants.minsize.ordinal() ];
	}

	public void setBachMinsize(String bachMinsize) {
		saveArray[ tableFldConstants.minsize.ordinal() ] = bachMinsize;
	}

	public String getBachMaxsize() {
		return (String) saveArray[ tableFldConstants.maxsize.ordinal() ];
	}

	public void setBachMaxsize(String bachMaxsize) {
		saveArray[ tableFldConstants.maxsize.ordinal() ] = bachMaxsize;
	}

	public String getBachDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setBachDuration(String bachDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = bachDuration;
	}

	public String getBachRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBachRemarks(String bachRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bachRemarks;
	}

	public String getBachStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setBachStatus(String bachStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = bachStatus;
	}

	public String getBachCompletedate() {
		return (String) saveArray[ tableFldConstants.completedate.ordinal() ];
	}

	public void setBachCompletedate(String bachCompletedate) {
		saveArray[ tableFldConstants.completedate.ordinal() ] = bachCompletedate;
	}

	public String getBachTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setBachTempfield2(String bachTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bachTempfield2;
	}

	public String getBachTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBachTempfield3(String bachTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bachTempfield3;
	}

	public String getBachTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBachTempfield4(String bachTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = bachTempfield4;
	}

	public String getBachTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBachTempfield5(String bachTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = bachTempfield5;
	}

	public String getBachActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBachActive(String bachActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bachActive;
	}

	public String getBachCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBachCreatedby(String bachCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bachCreatedby;
	}

	public String getBachCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBachCreatedon(String bachCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bachCreatedon;
		CommonMessage.debugMsg("in set bacthc created on");
	}

	public String getBachModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBachModifiedon(String bachModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bachModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public String getIsbatch() {
		return isbatch;
	}

	public void setIsbatch(String isbatch) {
		this.isbatch = isbatch;
	}
	
	public String getBachFromTime() {
		return bachFromTime;
	}
	
	public void setBachFromTime(String bachFromTime) {
		this.bachFromTime = bachFromTime;
	}
	

	public String getBachTillTime() {
		return bachTillTime;
	}
	
	public void setBachTillTime(String bachTillTime) {
		this.bachTillTime = bachTillTime;
	}
	


}

