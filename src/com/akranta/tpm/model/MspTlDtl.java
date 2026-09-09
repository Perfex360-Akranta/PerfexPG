package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MspTlDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, indicatorid, mpms_keyid, factoryid, sectionid, cellid
		, milestone, targetdate, completedate, responsibility, completedby
		, remarks, status, assignedto, flid, tempfield8, tempfield7
		, tempfield6, tempfield5, tempfield4, tempfield3, tempfield2
		, tempfield1, active, createdby, createdon, modifiedon
	}

	public MspTlDtl()
	{
		saveArray = new  Object [ 27 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getMspdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMspdKeyid(String mspdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mspdKeyid;
	}

	public String getMspdIndicatorid() {
		return (String) saveArray[ tableFldConstants.indicatorid.ordinal() ];
	}

	public void setMspdIndicatorid(String mspdIndicatorid) {
		saveArray[ tableFldConstants.indicatorid.ordinal() ] = mspdIndicatorid;
	}

	public String getMspdMpmsKeyid() {
		return (String) saveArray[ tableFldConstants.mpms_keyid.ordinal() ];
	}

	public void setMspdMpmsKeyid(String mspdMpmsKeyid) {
		saveArray[ tableFldConstants.mpms_keyid.ordinal() ] = mspdMpmsKeyid;
	}

	public String getMspdFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMspdFactoryid(String mspdFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = mspdFactoryid;
	}

	public String getMspdSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMspdSectionid(String mspdSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = mspdSectionid;
	}

	public String getMspdCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMspdCellid(String mspdCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mspdCellid;
	}

	public String getMspdMilestone() {
		return (String) saveArray[ tableFldConstants.milestone.ordinal() ];
	}

	public void setMspdMilestone(String mspdMilestone) {
		saveArray[ tableFldConstants.milestone.ordinal() ] = mspdMilestone;
	}

	public String getMspdTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setMspdTargetdate(String mspdTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = mspdTargetdate;
	}

	public String getMspdCompletedate() {
		return (String) saveArray[ tableFldConstants.completedate.ordinal() ];
	}

	public void setMspdCompletedate(String mspdCompletedate) {
		saveArray[ tableFldConstants.completedate.ordinal() ] = mspdCompletedate;
	}

	public String getMspdResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setMspdResponsibility(String mspdResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = mspdResponsibility;
	}

	public String getMspdCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setMspdCompletedby(String mspdCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = mspdCompletedby;
	}

	public String getMspdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMspdRemarks(String mspdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mspdRemarks;
	}

	public String getMspdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMspdStatus(String mspdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mspdStatus;
	}

	public String getMspdAssignedto() {
		return (String) saveArray[ tableFldConstants.assignedto.ordinal() ];
	}

	public void setMspdAssignedto(String mspdAssignedto) {
		saveArray[ tableFldConstants.assignedto.ordinal() ] = mspdAssignedto;
	}

	public String getMspdFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMspdFlid(String mspdFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mspdFlid;
	}

	public String getMspdTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setMspdTempfield8(String mspdTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = mspdTempfield8;
	}

	public String getMspdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setMspdTempfield7(String mspdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = mspdTempfield7;
	}

	public String getMspdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setMspdTempfield6(String mspdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = mspdTempfield6;
	}

	public String getMspdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMspdTempfield5(String mspdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mspdTempfield5;
	}

	public String getMspdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMspdTempfield4(String mspdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mspdTempfield4;
	}

	public String getMspdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMspdTempfield3(String mspdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mspdTempfield3;
	}

	public String getMspdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMspdTempfield2(String mspdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mspdTempfield2;
	}

	public String getMspdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMspdTempfield1(String mspdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mspdTempfield1;
	}

	public String getMspdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMspdActive(String mspdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mspdActive;
	}

	public String getMspdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMspdCreatedby(String mspdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mspdCreatedby;
	}

	public String getMspdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMspdCreatedon(String mspdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mspdCreatedon;
	}

	public String getMspdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMspdModifiedon(String mspdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mspdModifiedon;
	}

}

