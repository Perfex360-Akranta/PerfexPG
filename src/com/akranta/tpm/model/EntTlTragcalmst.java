package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.EntTlProgrammst.tableFldConstants;

public class EntTlTragcalmst {

	private EntTlTrgCalSession sessionmaster;
	private EntTlTrgCalSession trgflid;
	private EntTlTrgFaculty faculty;
	private EntTlTrgCalUnqp roleLink;
	private String allUniquePosition;
	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
		
		keyid, flid, location, dmt, jh, topicid, createddatetime, remarks
		,calendardate,general,uniquepstn,msd,chkcompleted,completeddate
		,completedby,maxduration,function,venue,permittedstnt,materialready
		,assessmentrequ,marksbased,filemanagerid,anchoredby,trainingfunction,rating,
		comments,topiccategory,tempfield6,tempfield7,tempfield8,tempfield9,tempfield10
		,active,createdby,createdon,modifiedon
	}

	public EntTlTragcalmst()
	{
		saveArray = new  Object [37];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	
	public String getEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcmKeyid(String  etcmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcmKeyid;
	}
    
	public String getEtcmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setEtcmFlid(String etcmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = etcmFlid;
	}
	
	
	public String getEtcmLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setEtcmLocation(String etcmLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = etcmLocation;
	}
	
	public String getEtcmDmt() {
		return (String) saveArray[ tableFldConstants.dmt.ordinal() ];
	}

	public void setEtcmDmt(String etcmDmt) {
		saveArray[ tableFldConstants.dmt.ordinal() ] = etcmDmt;
	}
	
	public String getEtcmJh() {
		return (String) saveArray[ tableFldConstants.jh.ordinal() ];
	}

	public void setEtcmJh(String etcmJh) {
		saveArray[ tableFldConstants.jh.ordinal() ] = etcmJh;
	}
	
	public String getEtcmTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setEtcmTopicid(String etcmTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = etcmTopicid;
	}
	
	public String getEtcmCreatedDateTime() {
		return (String) saveArray[ tableFldConstants.createddatetime.ordinal() ];
	}

	public void setEtcmCreatedDateTime(String etcmCreatedDateTime) {
		saveArray[ tableFldConstants.createddatetime.ordinal() ] = etcmCreatedDateTime;
	}
	
	public String getEtcmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEtcmRemarks(String etcmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = etcmRemarks;
	}
	
	
	public String getEtcmCalendarDate() {
		return (String) saveArray[ tableFldConstants.calendardate.ordinal() ];
	}

	public void setEtcmCalendarDate(String etcmCalendarDate) {
		saveArray[ tableFldConstants.calendardate.ordinal() ] = etcmCalendarDate;
	}
	
	public String getEtcmGeneral() {
		return (String) saveArray[ tableFldConstants.general.ordinal() ];
	}

	public void setEtcmGeneral(String etcmGeneral) {
		saveArray[ tableFldConstants.general.ordinal() ] = etcmGeneral;
	}
	
	public String getEtcmUniqueposition() {
		return (String) saveArray[ tableFldConstants.uniquepstn.ordinal() ];
	}

	public void setEtcmUniqueposition(String etcmUniqueposition) {
		saveArray[ tableFldConstants.uniquepstn.ordinal() ] = etcmUniqueposition;
	}
	
	public String getEtcmMSD() {
		return (String) saveArray[ tableFldConstants.msd.ordinal() ];
	}

	
	public void setEtcmMSD(String etcmMSD) {
		saveArray[ tableFldConstants.msd.ordinal() ] = etcmMSD;
	}
	
	public String getEtcmChkCompleted() { 
		return (String) saveArray[ tableFldConstants.chkcompleted.ordinal() ];
	}
	
	public void setEtcmChkCompleted(String etcmChkCompleted) {
		saveArray[ tableFldConstants.chkcompleted.ordinal() ] = etcmChkCompleted;
	}
	
	public String getEtcmCompletedDate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}
	
	public void setEtcmCompletedDate(String etcmCompletedDate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = etcmCompletedDate;
	}
	public String getEtcmCompletedBy() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}
	
	public void setEtcmCompletedBy(String etcmCompletedBy) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = etcmCompletedBy;
	}
	public String getEtcmMaxDuration() {
		return (String) saveArray[ tableFldConstants.maxduration.ordinal() ];
	}
	
	public void setEtcmMaxDuration(String etcmMaxDuration) {
		saveArray[ tableFldConstants.maxduration.ordinal() ] = etcmMaxDuration;
	}
	public String getEtcmFunction() {
		return (String) saveArray[ tableFldConstants.function.ordinal() ];
	}
	
	public void setEtcmFunction(String etcmFunction) {
		saveArray[ tableFldConstants.function.ordinal() ] = etcmFunction;
	}
	public String getEtcmVenue() {
		return (String) saveArray[ tableFldConstants.venue.ordinal() ];
	}
	
	public void setEtcmVenue(String etcmVenue) {
		saveArray[ tableFldConstants.venue.ordinal() ] = etcmVenue;
	}
     
	public String getEtcmPermittedStrength() {
		return (String) saveArray[ tableFldConstants. permittedstnt.ordinal() ];
	}
	
	public void setEtcmPermittedStrength(String etcmPermittedStrength) {
		saveArray[ tableFldConstants.permittedstnt.ordinal() ] = etcmPermittedStrength;
	}

	public String getEtcmMaterialsReady() {
		return (String) saveArray[ tableFldConstants.materialready.ordinal() ];
	}
	
	public void setEtcmMaterialsReady(String etcmMaterialsReady) {
		saveArray[ tableFldConstants.materialready.ordinal() ] = etcmMaterialsReady;
	}
	
	public String getEtcmAssessmentReq() {
		return (String) saveArray[ tableFldConstants.assessmentrequ.ordinal() ];
	}
	
	public void setEtcmAssessmentReq(String etcmAssessmentReq) {
		saveArray[ tableFldConstants.assessmentrequ.ordinal() ] = etcmAssessmentReq;
	}
	public String getEtcmMarksBased() {
		return (String) saveArray[ tableFldConstants. marksbased.ordinal() ];
	}
	
	public void setEtcmMarksBased(String etcmMarksBased) {
		saveArray[ tableFldConstants.marksbased.ordinal() ] = etcmMarksBased;
	}
	
	public String getEtcmFileManagedId() {
		return (String) saveArray[ tableFldConstants.filemanagerid.ordinal() ];
	}
	
	public void setEtcmFileManagedId(String etcmFileManagedId) {
		saveArray[ tableFldConstants.filemanagerid.ordinal() ] = etcmFileManagedId;
	}
	public void setEtcmAnchoredby(String etcmAnchoredby) {
		saveArray[ tableFldConstants.anchoredby.ordinal() ] = etcmAnchoredby;
	}
	
	public String getEtcmAnchoredby() {
		return (String) saveArray[ tableFldConstants.anchoredby.ordinal() ];
	}
	
	public void setEtcmTrainingfunction(String etcmTrainingfunction) {
		saveArray[ tableFldConstants.trainingfunction.ordinal() ] = etcmTrainingfunction;
	}
	
	public String getEtcmTrainingfunction() {
		return (String) saveArray[ tableFldConstants.trainingfunction.ordinal() ];
	}
	
	public void setEtcmRating(String etcmRating) {
		saveArray[ tableFldConstants.rating.ordinal() ] = etcmRating;
	}
	
	public String getEtcmRating() {
		return (String) saveArray[ tableFldConstants.rating.ordinal() ];
	}
	
	public void setEtcmComments(String etcmComments) {
		saveArray[ tableFldConstants.comments.ordinal() ] = etcmComments;
	}
	
	public String getEtcmComments() {
		return (String) saveArray[ tableFldConstants.comments.ordinal() ];
	}
	
	public void setEtcmTopiccategory(String etcmTopiccategory) {
		saveArray[ tableFldConstants.topiccategory.ordinal() ] = etcmTopiccategory;
	}
	
	public String getEtcmTopiccategory() {
		return (String) saveArray[ tableFldConstants.topiccategory.ordinal() ];
	}
	
	public void setEtcmTempfield6(String etcmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = etcmTempfield6;
	}
	
	public String getEtcmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}
	
	public void setEtcmTempfield7(String etcmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = etcmTempfield7;
	}
	
	public String getEtcmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}
	
	public void setEtcmTempfield8(String etcmTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = etcmTempfield8;
	}
	
	public String getEtcmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}
	
	public void setEtcmTempfield9(String etcmTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = etcmTempfield9;
	}
	
	public String getEtcmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}
	
	public void setEtcmTempfield10(String etcmTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = etcmTempfield10;
	}
	
	public String getEtcmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}
	
	public void setEtcmActive(String etcmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcmActive;
	}
	
	public String getEtcmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}
	
	public void setEtcmCreatedBy(String etcmCreatedBy) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etcmCreatedBy;
	}
	
	public String getEtcmCreatedBy() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	
	public void setEtcmCreatedOn(String etcmCreatedOn) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcmCreatedOn;
	}
	
	public String getEtcmCreatedOn() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}
	
	public void setEtcmModifiedOn(String etcmModifiedOn) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcmModifiedOn;
	}
	
	public String getEtcmModifiedOn() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}
	
	public EntTlTrgCalSession getsessionMaster(){
		return sessionmaster;
	}
    public void setsessionMaster(EntTlTrgCalSession sessionmaster){
    	this.sessionmaster=sessionmaster;
    }
    public EntTlTrgCalSession gettrgFlid(){
    	return trgflid;
    }
    public void settrgFlid(EntTlTrgCalSession trgflid){
    	this.trgflid=trgflid;
    }
    public EntTlTrgFaculty getFaculty() {
		return faculty;
	}

	public void setFaculty(EntTlTrgFaculty faculty){
		this.faculty = faculty;
	} 
	public void setRoleLink(EntTlTrgCalUnqp roleLink) {
		this.roleLink = roleLink;
	}

	public EntTlTrgCalUnqp getRoleLink() {
		return roleLink;
	}

    public void setAllUniquePosition(String allUniquePosition) {
		this.allUniquePosition = allUniquePosition;
	}
	public String getAllUniquePosition() {
		return allUniquePosition;
	}
}  
