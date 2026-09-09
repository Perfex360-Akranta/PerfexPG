package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class ProgrammstBean {
	private String formActionMode;
	private String formMode;
	private String disableForm;
	private String allUniquePosition;
	
	public ProgrammstBean(FormModes mode) {
		// TODO Auto-generated constructor stub
		if( FormModes.view == mode ){
			this.setDisableForm("true");
			this.formMode = FormModeConsts.view;
		}
		else{
			this.setDisableForm("false");
			this.formMode = FormModeConsts.create;
		}	
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
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getDisableForm() {
		return disableForm;
	}

	public void setAllUniquePosition(String allUniquePosition) {
		this.allUniquePosition = allUniquePosition;
	}
	public String getAllUniquePosition() {
		return allUniquePosition;
	}
	
}
