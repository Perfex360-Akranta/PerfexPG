package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EhsTlInspectiondtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, insm_keyid, aspect, observation, action_required, responsibility
		, target_date, status, completed_date, completed_by, remarks
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public EhsTlInspectiondtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getInsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setInsdKeyid(String insdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = insdKeyid;
	}

	public String getInsdInsmKeyid() {
		return (String) saveArray[ tableFldConstants.insm_keyid.ordinal() ];
	}

	public void setInsdInsmKeyid(String insdInsmKeyid) {
		saveArray[ tableFldConstants.insm_keyid.ordinal() ] = insdInsmKeyid;
	}

	public String getInsdAspect() {
		return (String) saveArray[ tableFldConstants.aspect.ordinal() ];
	}

	public void setInsdAspect(String insdAspect) {
		saveArray[ tableFldConstants.aspect.ordinal() ] = insdAspect;
	}

	public String getInsdObservation() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setInsdObservation(String insdObservation) {
		saveArray[ tableFldConstants.observation.ordinal() ] = insdObservation;
	}

	public String getInsdActionRequired() {
		return (String) saveArray[ tableFldConstants.action_required.ordinal() ];
	}

	public void setInsdActionRequired(String insdActionRequired) {
		saveArray[ tableFldConstants.action_required.ordinal() ] = insdActionRequired;
	}

	public String getInsdResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setInsdResponsibility(String insdResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = insdResponsibility;
	}

	public String getInsdTargetDate() {
		return (String) saveArray[ tableFldConstants.target_date.ordinal() ];
	}

	public void setInsdTargetDate(String insdTargetDate) {
		saveArray[ tableFldConstants.target_date.ordinal() ] = insdTargetDate;
	}

	public String getInsdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setInsdStatus(String insdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = insdStatus;
	}

	public String getInsdCompletedDate() {
		return (String) saveArray[ tableFldConstants.completed_date.ordinal() ];
	}

	public void setInsdCompletedDate(String insdCompletedDate) {
		saveArray[ tableFldConstants.completed_date.ordinal() ] = insdCompletedDate;
	}

	public String getInsdCompletedBy() {
		return (String) saveArray[ tableFldConstants.completed_by.ordinal() ];
	}

	public void setInsdCompletedBy(String insdCompletedBy) {
		saveArray[ tableFldConstants.completed_by.ordinal() ] = insdCompletedBy;
	}

	public String getInsdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setInsdRemarks(String insdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = insdRemarks;
	}

	public String getInsdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setInsdTempfield1(String insdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = insdTempfield1;
	}

	public String getInsdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setInsdTempfield2(String insdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = insdTempfield2;
	}

	public String getInsdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setInsdTempfield3(String insdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = insdTempfield3;
	}

	public String getInsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setInsdActive(String insdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = insdActive;
	}

	public String getInsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setInsdCreatedby(String insdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = insdCreatedby;
	}

	public String getInsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setInsdCreatedon(String insdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = insdCreatedon;
	}

	public String getInsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setInsdModifiedon(String insdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = insdModifiedon;
	}

}

