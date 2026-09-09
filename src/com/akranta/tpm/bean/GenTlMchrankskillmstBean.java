package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class GenTlMchrankskillmstBean {
	private FormModes formMode;
	private String formActionMode;
	
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
}
