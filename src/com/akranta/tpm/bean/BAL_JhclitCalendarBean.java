package com.akranta.tpm.bean;

public class BAL_JhclitCalendarBean {

	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String responsibility;
	private String ClisIstoolsreq;
	private String actionmode;
	public BAL_JhclitCalendarBean()
	{
		this.formMode = "CREATE";
		
		
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public BAL_JhclitCalendarBean(String mode) {
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
	public void setClisIstoolsreq(String clisIstoolsreq) {
		ClisIstoolsreq = clisIstoolsreq;
	}
	public String getClisIstoolsreq() {
		return ClisIstoolsreq;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	
	
	
}

