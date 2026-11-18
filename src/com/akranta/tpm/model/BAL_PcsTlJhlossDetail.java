package com.akranta.tpm.model;

import java.util.List;

import com.akranta.tpm.model.PcsTlLosscapture.tableFldConstants;

public class BAL_PcsTlJhlossDetail {
private  Object [] saveArray = null;  
private List<BAL_PcsTlJhlossDetail> jhdetails;
//private String existLossNumber;
//private String jhLossNo;

	
	public enum   tableFldConstants
	{
	    keyid,  lossid, machineid,lubTime,cleanTime,tightime,inspectTime,tempfiled1,tempField2,tempField3,tempField4,  createdby
		, createdon, modifiedon
	}

	public BAL_PcsTlJhlossDetail()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getJhlosKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJhlossKeyid(String JhlossKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = JhlossKeyid;
	}

	public String getJhlossMachine() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setJhlossMachine(String MachineNumber) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = MachineNumber;
	}

	public String getJhlossId() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setJhlossId(String lossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = lossid;
	}

	public String getJhlossLubtime() {
		return (String) saveArray[ tableFldConstants.lubTime.ordinal() ];
	}

	public void setJhlossLubTime(String LUB) {
		saveArray[ tableFldConstants.lubTime.ordinal() ] = LUB;
	}
	public String getJhlossTightTime() {
		return (String) saveArray[ tableFldConstants.tightime.ordinal() ];
	}
	
	public void setJhlossCleanTime(String txtCleanTime) {
		saveArray[ tableFldConstants.cleanTime.ordinal() ] = txtCleanTime;
	}
	public String getJhlossCleanTime() {
		return (String) saveArray[ tableFldConstants.cleanTime.ordinal() ];
	}

	public void setJhlossTightTime(String TIGHTEN) {
		saveArray[ tableFldConstants.tightime.ordinal() ] = TIGHTEN;
	}
	public String getJhlossInspectTime() {
		return (String) saveArray[ tableFldConstants.inspectTime.ordinal() ];
	}

	public void setJhlossInspectTime(String INSPECT) {
		saveArray[ tableFldConstants.inspectTime.ordinal() ] = INSPECT;
	}
	
	//
	public String getJhlossTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}
	public void setJhlossTempfield1(String JhlossTempfield1 ){
		saveArray[tableFldConstants.tempfiled1.ordinal()]=JhlossTempfield1;
	}
	public String getJhlossTempfield2() {
		return (String) saveArray[ tableFldConstants.tempField2.ordinal() ];
	}
	public void setJhlossTempfield2(String JhlossTempfield2){
		saveArray[tableFldConstants.tempField2.ordinal()]= JhlossTempfield2;
	}
	public String getJhlossTempfield3() {
		return (String) saveArray[ tableFldConstants.tempField3.ordinal() ];
	}
	public void setJhlossTempfield3(String JhlossTempfield3){
		saveArray[tableFldConstants.tempField3.ordinal()]= JhlossTempfield3;
	}
	public String getJhlossCreatedOn() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJhlossCreatedOn(String createdOn) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = createdOn;
	}
	public String getJhlossCreatedBy() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJhlossCreatedBy(String createdBy) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = createdBy;
	}
	public String getJhlossModifiedOn() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJhlossModifiedOn(String modified) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modified;
	}
	public List <BAL_PcsTlJhlossDetail> getJhDetails() {
		return jhdetails; 
	}

	public void setjhDetails(List <BAL_PcsTlJhlossDetail> jhDetails) {
		this.jhdetails = jhDetails;
	}
	
	/*
	public String getExistLossNumber() {
		return existLossNumber;
	}

	public void setExistDocNumber(String existlossNumber) {
		existLossNumber = existLossNumber;
	}

	public String getlossId() {
		return jhLossNo;
	}

	public void setRefDocId(String jhLossNo) {
		this.jhLossNo = jhLossNo;
	}
*/
}
