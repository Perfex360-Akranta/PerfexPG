package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrainingfeedmst {

	private  Object [] saveArray = null;  
	
	private List<EntTlFeedbackformdtl> FeedbackFormdetail ;
	private List<EntTlFeedbackformdtl> FeedbackFormdetailIp ;
	public enum   tableFldConstants
	{
		keyid, title, date, duration, faculty, participantname, designation
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, flid, active, createdby, createdon, modifiedon
	}

	public EntTlTrainingfeedmst()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTfmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTfmsKeyid(String tfmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tfmsKeyid;
	}

	public String getTfmsTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setTfmsTitle(String tfmsTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = tfmsTitle;
	}

	public String getTfmsDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setTfmsDate(String tfmsDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = tfmsDate;
	}

	public String getTfmsDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setTfmsDuration(String tfmsDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = tfmsDuration;
	}

	public String getTfmsFaculty() {
		return (String) saveArray[ tableFldConstants.faculty.ordinal() ];
	}

	public void setTfmsFaculty(String tfmsFaculty) {
		saveArray[ tableFldConstants.faculty.ordinal() ] = tfmsFaculty;
	}

	public String getTfmsParticipantname() {
		return (String) saveArray[ tableFldConstants.participantname.ordinal() ];
	}

	public void setTfmsParticipantname(String tfmsParticipantname) {
		saveArray[ tableFldConstants.participantname.ordinal() ] = tfmsParticipantname;
	}

	public String getTfmsDesignation() {
		return (String) saveArray[ tableFldConstants.designation.ordinal() ];
	}

	public void setTfmsDesignation(String tfmsDesignation) {
		saveArray[ tableFldConstants.designation.ordinal() ] = tfmsDesignation;
	}

	public String getTfmsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setTfmsRemarks(String tfmsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = tfmsRemarks;
	}

	public String getTfmsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTfmsTempfield1(String tfmsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tfmsTempfield1;
	}

	public String getTfmsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTfmsTempfield2(String tfmsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tfmsTempfield2;
	}

	public String getTfmsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTfmsTempfield3(String tfmsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tfmsTempfield3;
	}

	public String getTfmsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTfmsTempfield4(String tfmsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tfmsTempfield4;
	}

	public String getTfmsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTfmsTempfield5(String tfmsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tfmsTempfield5;
	}

	public String getTfmsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTfmsFlid(String tfmsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tfmsFlid;
	}

	public String getTfmsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTfmsActive(String tfmsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tfmsActive;
	}

	public String getTfmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTfmsCreatedby(String tfmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tfmsCreatedby;
	}

	public String getTfmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTfmsCreatedon(String tfmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tfmsCreatedon;
	}

	public String getTfmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTfmsModifiedon(String tfmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tfmsModifiedon;
	}
	public void setSaveArray(Object[] saveArray) 
	{
		this.saveArray = saveArray;
		
	}

	public List<EntTlFeedbackformdtl> getFeedbackFormdeatils() {
		// TODO Auto-generated method stub
		return FeedbackFormdetail;
	}
	public void setFeedbackFormdeatils(List<EntTlFeedbackformdtl> feedbackdetails) {
		this.FeedbackFormdetail = feedbackdetails;
	}
	public List<EntTlFeedbackformdtl> getFeedbackFormdetailIp() {
		// TODO Auto-generated method stub
		return FeedbackFormdetailIp;
	}
	public void setFeedbackFormdetailIp(List<EntTlFeedbackformdtl> feedbackdetailsip) {
		this.FeedbackFormdetailIp = feedbackdetailsip;
	}
}

