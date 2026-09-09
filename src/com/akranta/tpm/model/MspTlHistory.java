package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MspTlHistory {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, mspd_keyid, targetdate, completedate, responsibility, completedby
		, remarks, status, assignedto, tempfield9, tempfield8, tempfield7
		, tempfield6, tempfield5, tempfield4, tempfield3, tempfield2
		, tempfield1, active, createdby, createdon, modifiedon
	}

	public MspTlHistory()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMphiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMphiKeyid(String mphiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mphiKeyid;
	}

	public String getMphiMspdKeyid() {
		return (String) saveArray[ tableFldConstants.mspd_keyid.ordinal() ];
	}

	public void setMphiMspdKeyid(String mphiMspdKeyid) {
		saveArray[ tableFldConstants.mspd_keyid.ordinal() ] = mphiMspdKeyid;
	}

	public String getMphiTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setMphiTargetdate(String mphiTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = mphiTargetdate;
	}

	public String getMphiCompletedate() {
		return (String) saveArray[ tableFldConstants.completedate.ordinal() ];
	}

	public void setMphiCompletedate(String mphiCompletedate) {
		saveArray[ tableFldConstants.completedate.ordinal() ] = mphiCompletedate;
	}

	public String getMphiResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setMphiResponsibility(String mphiResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = mphiResponsibility;
	}

	public String getMphiCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setMphiCompletedby(String mphiCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = mphiCompletedby;
	}

	public String getMphiRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMphiRemarks(String mphiRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mphiRemarks;
	}

	public String getMphiStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMphiStatus(String mphiStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mphiStatus;
	}

	public String getMphiAssignedto() {
		return (String) saveArray[ tableFldConstants.assignedto.ordinal() ];
	}

	public void setMphiAssignedto(String mphiAssignedto) {
		saveArray[ tableFldConstants.assignedto.ordinal() ] = mphiAssignedto;
	}

	public String getMphiTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setMphiTempfield9(String mphiTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = mphiTempfield9;
	}

	public String getMphiTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setMphiTempfield8(String mphiTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = mphiTempfield8;
	}

	public String getMphiTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setMphiTempfield7(String mphiTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = mphiTempfield7;
	}

	public String getMphiTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setMphiTempfield6(String mphiTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = mphiTempfield6;
	}

	public String getMphiTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMphiTempfield5(String mphiTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mphiTempfield5;
	}

	public String getMphiTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMphiTempfield4(String mphiTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mphiTempfield4;
	}

	public String getMphiTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMphiTempfield3(String mphiTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mphiTempfield3;
	}

	public String getMphiTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMphiTempfield2(String mphiTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mphiTempfield2;
	}

	public String getMphiTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMphiTempfield1(String mphiTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mphiTempfield1;
	}

	public String getMphiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMphiActive(String mphiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mphiActive;
	}

	public String getMphiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMphiCreatedby(String mphiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mphiCreatedby;
	}

	public String getMphiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMphiCreatedon(String mphiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mphiCreatedon;
	}

	public String getMphiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMphiModifiedon(String mphiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mphiModifiedon;
	}

}

