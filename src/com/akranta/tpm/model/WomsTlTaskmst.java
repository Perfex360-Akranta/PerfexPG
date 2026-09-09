package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class WomsTlTaskmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, task, woms_keyid, job_type, frequency, assemblyid, status
		, observation, responsibility, target_date, planned_duration
		, actual_duration, action_taken, cbm_reading, zone_color, cbm_action
		, adjusted_reading, next_due_date, remarks, ideal_condition_type
		, ideal_condition, actual_condition, noti_refno, noti_status
		, woms_refno, woms_status, extserviceflag, extrepairflag, activityno, actionplanid, temp5
		, temp6, temp7, temp8, temp9, createdby, createdon, modifiedon
	}

	public WomsTlTaskmst()
	{
		saveArray = new  Object [ 38 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWtmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWtmsKeyid(String wtmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wtmsKeyid;
	}

	public String getWtmsTask() {
		return (String) saveArray[ tableFldConstants.task.ordinal() ];
	}

	public void setWtmsTask(String wtmsTask) {
		saveArray[ tableFldConstants.task.ordinal() ] = wtmsTask;
	}

	public String getWtmsWomsKeyid() {
		return (String) saveArray[ tableFldConstants.woms_keyid.ordinal() ];
	}

	public void setWtmsWomsKeyid(String wtmsWomsKeyid) {
		saveArray[ tableFldConstants.woms_keyid.ordinal() ] = wtmsWomsKeyid;
	}

	public String getWtmsJobType() {
		return (String) saveArray[ tableFldConstants.job_type.ordinal() ];
	}

	public void setWtmsJobType(String wtmsJobType) {
		saveArray[ tableFldConstants.job_type.ordinal() ] = wtmsJobType;
	}

	public String getWtmsFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setWtmsFrequency(String wtmsFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = wtmsFrequency;
	}

	public String getWtmsAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setWtmsAssemblyid(String wtmsAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = wtmsAssemblyid;
	}

	public String getWtmsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWtmsStatus(String wtmsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wtmsStatus;
	}

	public String getWtmsObservation() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setWtmsObservation(String wtmsObservation) {
		saveArray[ tableFldConstants.observation.ordinal() ] = wtmsObservation;
	}

	public String getWtmsResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setWtmsResponsibility(String wtmsResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = wtmsResponsibility;
	}

	public String getWtmsTargetDate() {
		return (String) saveArray[ tableFldConstants.target_date.ordinal() ];
	}

	public void setWtmsTargetDate(String wtmsTargetDate) {
		saveArray[ tableFldConstants.target_date.ordinal() ] = wtmsTargetDate;
	}

	public String getWtmsPlannedDuration() {
		return (String) saveArray[ tableFldConstants.planned_duration.ordinal() ];
	}

	public void setWtmsPlannedDuration(String wtmsPlannedDuration) {
		saveArray[ tableFldConstants.planned_duration.ordinal() ] = wtmsPlannedDuration;
	}

	public String getWtmsActualDuration() {
		return (String) saveArray[ tableFldConstants.actual_duration.ordinal() ];
	}

	public void setWtmsActualDuration(String wtmsActualDuration) {
		saveArray[ tableFldConstants.actual_duration.ordinal() ] = wtmsActualDuration;
	}

	public String getWtmsActionTaken() {
		return (String) saveArray[ tableFldConstants.action_taken.ordinal() ];
	}

	public void setWtmsActionTaken(String wtmsActionTaken) {
		saveArray[ tableFldConstants.action_taken.ordinal() ] = wtmsActionTaken;
	}

	public String getWtmsCbmReading() {
		return (String) saveArray[ tableFldConstants.cbm_reading.ordinal() ];
	}

	public void setWtmsCbmReading(String wtmsCbmReading) {
		saveArray[ tableFldConstants.cbm_reading.ordinal() ] = wtmsCbmReading;
	}

	public String getWtmsZoneColor() {
		return (String) saveArray[ tableFldConstants.zone_color.ordinal() ];
	}

	public void setWtmsZoneColor(String wtmsZoneColor) {
		saveArray[ tableFldConstants.zone_color.ordinal() ] = wtmsZoneColor;
	}

	public String getWtmsCbmAction() {
		return (String) saveArray[ tableFldConstants.cbm_action.ordinal() ];
	}

	public void setWtmsCbmAction(String wtmsCbmAction) {
		saveArray[ tableFldConstants.cbm_action.ordinal() ] = wtmsCbmAction;
	}

	public String getWtmsAdjustedReading() {
		return (String) saveArray[ tableFldConstants.adjusted_reading.ordinal() ];
	}

	public void setWtmsAdjustedReading(String wtmsAdjustedReading) {
		saveArray[ tableFldConstants.adjusted_reading.ordinal() ] = wtmsAdjustedReading;
	}

	public String getWtmsNextDueDate() {
		return (String) saveArray[ tableFldConstants.next_due_date.ordinal() ];
	}

	public void setWtmsNextDueDate(String wtmsNextDueDate) {
		saveArray[ tableFldConstants.next_due_date.ordinal() ] = wtmsNextDueDate;
	}

	public String getWtmsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWtmsRemarks(String wtmsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = wtmsRemarks;
	}

	public String getWtmsIdealConditionType() {
		return (String) saveArray[ tableFldConstants.ideal_condition_type.ordinal() ];
	}

	public void setWtmsIdealConditionType(String wtmsIdealConditionType) {
		saveArray[ tableFldConstants.ideal_condition_type.ordinal() ] = wtmsIdealConditionType;
	}

	public String getWtmsIdealCondition() {
		return (String) saveArray[ tableFldConstants.ideal_condition.ordinal() ];
	}

	public void setWtmsIdealCondition(String wtmsIdealCondition) {
		saveArray[ tableFldConstants.ideal_condition.ordinal() ] = wtmsIdealCondition;
	}

	public String getWtmsActualCondition() {
		return (String) saveArray[ tableFldConstants.actual_condition.ordinal() ];
	}

	public void setWtmsActualCondition(String wtmsActualCondition) {
		saveArray[ tableFldConstants.actual_condition.ordinal() ] = wtmsActualCondition;
	}

	public String getWtmsNotiRefno() {
		return (String) saveArray[ tableFldConstants.noti_refno.ordinal() ];
	}

	public void setWtmsNotiRefno(String wtmsNotiRefno) {
		saveArray[ tableFldConstants.noti_refno.ordinal() ] = wtmsNotiRefno;
	}

	public String getWtmsNotiStatus() {
		return (String) saveArray[ tableFldConstants.noti_status.ordinal() ];
	}

	public void setWtmsNotiStatus(String wtmsNotiStatus) {
		saveArray[ tableFldConstants.noti_status.ordinal() ] = wtmsNotiStatus;
	}

	public String getWtmsWomsRefno() {
		return (String) saveArray[ tableFldConstants.woms_refno.ordinal() ];
	}

	public void setWtmsWomsRefno(String wtmsWomsRefno) {
		saveArray[ tableFldConstants.woms_refno.ordinal() ] = wtmsWomsRefno;
	}

	public String getWtmsWomsStatus() {
		return (String) saveArray[ tableFldConstants.woms_status.ordinal() ];
	}

	public void setWtmsWomsStatus(String wtmsWomsStatus) {
		saveArray[ tableFldConstants.woms_status.ordinal() ] = wtmsWomsStatus;
	}

	public String getWtmsExtrepairflag() {
		return (String) saveArray[ tableFldConstants.extrepairflag.ordinal() ];
	}

	public void setWtmsExtrepairflag(String wtmsExtrepairflag) {
		saveArray[ tableFldConstants.extrepairflag.ordinal() ] = wtmsExtrepairflag;
	}

	public String getWtmsExtserviceflag() {
		return (String) saveArray[ tableFldConstants.extserviceflag.ordinal() ];
	}

	public void setWtmsExtserviceflag(String wtmsExtserviceflag) {
		saveArray[ tableFldConstants.extserviceflag.ordinal() ] = wtmsExtserviceflag;
	}

	public String getWtmsActivityno() {
		return (String) saveArray[ tableFldConstants.activityno.ordinal() ];
	}

	public void setWtmsActivityno(String wtmsActivityno) {
		saveArray[ tableFldConstants.activityno.ordinal() ] = wtmsActivityno;
	}

	public String getWtmsActionplanid() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setWtmsActionplanid(String wtmsActionplanid) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = wtmsActionplanid;
	}

	public String getWtmsTemp5() {
		return (String) saveArray[ tableFldConstants.temp5.ordinal() ];
	}

	public void setWtmsTemp5(String wtmsTemp5) {
		saveArray[ tableFldConstants.temp5.ordinal() ] = wtmsTemp5;
	}

	public String getWtmsTemp6() {
		return (String) saveArray[ tableFldConstants.temp6.ordinal() ];
	}

	public void setWtmsTemp6(String wtmsTemp6) {
		saveArray[ tableFldConstants.temp6.ordinal() ] = wtmsTemp6;
	}

	public String getWtmsTemp7() {
		return (String) saveArray[ tableFldConstants.temp7.ordinal() ];
	}

	public void setWtmsTemp7(String wtmsTemp7) {
		saveArray[ tableFldConstants.temp7.ordinal() ] = wtmsTemp7;
	}

	public String getWtmsTemp8() {
		return (String) saveArray[ tableFldConstants.temp8.ordinal() ];
	}

	public void setWtmsTemp8(String wtmsTemp8) {
		saveArray[ tableFldConstants.temp8.ordinal() ] = wtmsTemp8;
	}

	public String getWtmsTemp9() {
		return (String) saveArray[ tableFldConstants.temp9.ordinal() ];
	}

	public void setWtmsTemp9(String wtmsTemp9) {
		saveArray[ tableFldConstants.temp9.ordinal() ] = wtmsTemp9;
	}

	public String getWtmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWtmsCreatedby(String wtmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wtmsCreatedby;
	}

	public String getWtmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWtmsCreatedon(String wtmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wtmsCreatedon;
	}

	public String getWtmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWtmsModifiedon(String wtmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wtmsModifiedon;
	}

}

