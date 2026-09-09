package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class FishBoneBean {
	private String formActionMode;
	private FormModes formMode;
	private String formModes;
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormModes(String formModes) {
		this.formModes = formModes;
	}
	public String getFormModes() {
		return formModes;
	}


}
