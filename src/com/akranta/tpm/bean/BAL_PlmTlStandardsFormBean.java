package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class BAL_PlmTlStandardsFormBean {

	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String responsibility;
	private String Activitysub;
	private String bdmDockKey;
	private char ClisIstoolsreq;
	private FormModes actionmode;
	public BAL_PlmTlStandardsFormBean()
	{
		this.formMode = "CREATE";
		
		
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public BAL_PlmTlStandardsFormBean(FormModes mode) {
		// TODO Auto-generated constructor stub
	
		this.setActionmode(mode);
		
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setClisIstoolsreq(char clisIstoolsreq) {
		ClisIstoolsreq = clisIstoolsreq;
	}
	public char getClisIstoolsreq() {
		return ClisIstoolsreq;
	}
	public void setActionmode(FormModes mode) {
		this.actionmode = mode;
	}
	public FormModes getActionmode() {
		return actionmode;
	}
	public void setActivitysub(String activitysub) {
		Activitysub = activitysub;
	}
	public String getActivitysub() {
		return Activitysub;
	}
	public void setBdmDockKey(String bdmDockKey) {
		this.bdmDockKey = bdmDockKey;
	}
	public String getBdmDockKey() {
		return bdmDockKey;
	}
	
	
	
}
