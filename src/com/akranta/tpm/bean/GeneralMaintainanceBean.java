package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GeneralMaintainanceBean {

	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String responsibility;
	private String occuredTime;
	private String workstartTime;
	private String workendTime;
	private String actionmode;
	private String disableForm;
	
	public GeneralMaintainanceBean()
	{
		this.formMode = FormModeConsts.create;
		
		
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public GeneralMaintainanceBean(FormModes mode) {
		
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
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getResponsibility() {
		return responsibility;
	}

	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setOccuredTime(String occuredTime) {
		this.occuredTime = occuredTime;
	}
	public String getOccuredTime() {
		return occuredTime;
	}
	
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getDisableForm() {
		return disableForm;
	}
	public void setFormMode(FormModes mode) {
		// TODO Auto-generated method stub
		
	}
	public void setWorkstartTime(String workstartTime) {
		this.workstartTime = workstartTime;
	}
	public String getWorkstartTime() {
		return workstartTime;
	}
	public void setWorkendTime(String workendTime) {
		this.workendTime = workendTime;
	}
	public String getWorkendTime() {
		return workendTime;
	}
	
	
	
}

