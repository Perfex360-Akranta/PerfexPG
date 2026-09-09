package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class EmpMangerLinkBean {
	
	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	
	
	
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	

}
