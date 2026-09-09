package com.akranta.tpm.bean;

public class MilestoneBean {
	private String formMode;
	private String milestoneFromDt;
	private String milestoneToDt;
	private String revisedTargetDate;
	private String dtlTargetDate;
	private String dtlCompletedDate;
	private String EnableDate;
	private String MilestnFromDt;
	private String MilestnToDt;
	private String MilestoneFromDupDt;
	private String MilestoneToDupDt;
	private String title;
	
	private  Object [] saveArray = null;	
	
	
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	
	public String getFormMode() {
		return formMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getMilestoneFromDt() {
		return milestoneFromDt;
	}
	public void setMilestoneFromDt(String milestoneFromDt) {
		this.milestoneFromDt = milestoneFromDt;
	}
	public String getMilestoneToDt() {
		return milestoneToDt;
	}
	public void setMilestoneToDt(String milestoneToDt) {
		this.milestoneToDt = milestoneToDt;
	}
	public String getRevisedTargetDate() {
		return revisedTargetDate;
	}
	public void setRevisedTargetDate(String revisedTargetDate) {
		this.revisedTargetDate = revisedTargetDate;
	}
	public String getDtlTargetDate() {
		return dtlTargetDate;
	}
	public void setDtlTargetDate(String dtlTargetDate) {
		this.dtlTargetDate = dtlTargetDate;
	}
	public String getDtlCompletedDate() {
		return dtlCompletedDate;
	}
	public void setDtlCompletedDate(String dtlCompletedDate) {
		this.dtlCompletedDate = dtlCompletedDate;
	}
	public String getEnableDate() {
		return EnableDate;
	}
	public void setEnableDate(String enableDate) {
		EnableDate = enableDate;
	}
	public String getMilestnFromDt() {
		return MilestnFromDt;
	}
	public void setMilestnFromDt(String milestnFromDt) {
		MilestnFromDt = milestnFromDt;
	}
	public String getMilestnToDt() {
		return MilestnToDt;
	}
	public void setMilestnToDt(String milestnToDt) {
		MilestnToDt = milestnToDt;
	}
	public String getMilestoneFromDupDt() {
		return MilestoneFromDupDt;
	}
	public void setMilestoneFromDupDt(String milestoneFromDupDt) {
		MilestoneFromDupDt = milestoneFromDupDt;
	}
	public String getMilestoneToDupDt() {
		return MilestoneToDupDt;
	}
	public void setMilestoneToDupDt(String milestoneToDupDt) {
		MilestoneToDupDt = milestoneToDupDt;
	}
	public void setTitle(String title) {    //tttttt
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
}
