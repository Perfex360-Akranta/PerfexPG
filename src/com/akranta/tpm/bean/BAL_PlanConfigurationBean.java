package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class BAL_PlanConfigurationBean {
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String actionmode;
	private String disableForm;
	private String cmbCostcentreid;
	private String chkApplytoall; 
	
	public BAL_PlanConfigurationBean()
	{
		this.formMode = FormModeConsts.create;
		
	}
	public BAL_PlanConfigurationBean(FormModes mode) {
		
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
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getDisableForm() {
		return disableForm;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setCmbCostcentreid(String cmbCostcentreid) {
		this.cmbCostcentreid = cmbCostcentreid;
	}
	public String getCmbCostcentreid() {
		return cmbCostcentreid;
	}
	public void setChkApplytoall(String chkApplytoall) {
		this.chkApplytoall = chkApplytoall;
	}
	public String getChkApplytoall() {
		return chkApplytoall;
	}
	
}
