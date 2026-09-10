package com.akranta.tpm.bean;

public class BAL_CliTlStandardFormBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String responsibility;
	private String ClisIstoolsreq;
	private String actionmode;
	private String disableForm;
	private String jhinputkeyid;
	private String bdmDockKey;
	private String inactiveDate;
	public BAL_CliTlStandardFormBean()
	{
		this.formMode = "CREATE";
		
		
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public BAL_CliTlStandardFormBean(String mode) {
		// TODO Auto-generated constructor stub
	
		this.setActionmode(mode);
		System.out.println("bean Mode  :"+mode);
		if(mode.equals("view")){
			this.setDisableForm("true");
			System.out.println("jh viewdisableform"+this.getDisableForm());
		}
		else if(mode.equals("create")&& this.getJhinputkeyid()!= null){
			this.setDisableForm("true");
			System.out.println("jh input dbl clik  disableform");
		}
		else{
			this.setDisableForm("false");}	
		
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
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getDisableForm() {
		return disableForm;
	}
	public void setJhinputkeyid(String jhinputkeyid) {
		this.jhinputkeyid = jhinputkeyid;
	}
	public String getJhinputkeyid() {
		return jhinputkeyid;
	}
	public void setBdmDockKey(String bdmDockKey) {
		this.bdmDockKey = bdmDockKey;
	}
	public String getBdmDockKey() {
		return bdmDockKey;
	}
	public void setInactiveDate(String inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	public String getInactiveDate() {
		return inactiveDate;
	}
	
	
	
}
