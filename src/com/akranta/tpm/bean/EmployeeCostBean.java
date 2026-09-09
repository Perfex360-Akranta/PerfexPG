package com.akranta.tpm.bean;

public class EmployeeCostBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	
	public EmployeeCostBean()
	{
		this.formMode = "CREATE";
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
	
}
