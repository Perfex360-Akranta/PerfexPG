package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBatchSchedule {

	private  Object [] saveArray = null;  
	private List <EntTlBatchSchedule> entTlBatchScheduleList ;

	public enum   tableFldConstants
	{
		keyid, bach_keyid, prog_keyid, ecal_keyid, schedule_date, duration
		, fromtime, tilltime, status, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlBatchSchedule()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	
	public void setEntTlBatchScheduleList(List <EntTlBatchSchedule> entTlRoleSkillRatingList) {
		this.entTlBatchScheduleList =  entTlRoleSkillRatingList;
	}
	public List<EntTlBatchSchedule> getEntTlBatchScheduleList() 
	{
		return entTlBatchScheduleList;
	}
	
	public String getBsdlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBsdlKeyid(String bsdlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bsdlKeyid;
	}

	public String getBsdlBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setBsdlBachKeyid(String bsdlBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = bsdlBachKeyid;
	}

	public String getBsdlProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setBsdlProgKeyid(String bsdlProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = bsdlProgKeyid;
	}

	public String getBsdlEcalKeyid() {
		return (String) saveArray[ tableFldConstants.ecal_keyid.ordinal() ];
	}

	public void setBsdlEcalKeyid(String bsdlEcalKeyid) {
		saveArray[ tableFldConstants.ecal_keyid.ordinal() ] = bsdlEcalKeyid;
	}

	public String getBsdlScheduleDate() {
		return (String) saveArray[ tableFldConstants.schedule_date.ordinal() ];
	}

	public void setBsdlScheduleDate(String bsdlScheduleDate) {
		saveArray[ tableFldConstants.schedule_date.ordinal() ] = bsdlScheduleDate;
	}

	public String getBsdlDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setBsdlDuration(String bsdlDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = bsdlDuration;
	}

	public String getBsdlFromtime() {
		return (String) saveArray[ tableFldConstants.fromtime.ordinal() ];
	}

	public void setBsdlFromtime(String bsdlFromtime) {
		saveArray[ tableFldConstants.fromtime.ordinal() ] = bsdlFromtime;
	}

	public String getBsdlTilltime() {
		return (String) saveArray[ tableFldConstants.tilltime.ordinal() ];
	}

	public void setBsdlTilltime(String bsdlTilltime) {
		saveArray[ tableFldConstants.tilltime.ordinal() ] = bsdlTilltime;
	}

	public String getBsdlStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setBsdlStatus(String bsdlStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = bsdlStatus;
	}

	public String getBsdlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setBsdlTempfield1(String bsdlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = bsdlTempfield1;
	}

	public String getBsdlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setBsdlTempfield2(String bsdlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bsdlTempfield2;
	}

	public String getBsdlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBsdlTempfield3(String bsdlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bsdlTempfield3;
	}

	public String getBsdlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBsdlTempfield4(String bsdlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = bsdlTempfield4;
	}

	public String getBsdlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBsdlTempfield5(String bsdlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = bsdlTempfield5;
	}

	public String getBsdlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBsdlActive(String bsdlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bsdlActive;
	}

	public String getBsdlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBsdlCreatedby(String bsdlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bsdlCreatedby;
	}

	public String getBsdlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBsdlCreatedon(String bsdlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bsdlCreatedon;
	}

	public String getBsdlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBsdlModifiedon(String bsdlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bsdlModifiedon;
	}

}

