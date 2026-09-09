package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class SkillCheckListBean {
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String responsibility;
	private boolean disableForm ;
	private String actionmode;
	private boolean disableChekKeyid;
	private boolean disableChekSkilKeyid;
	private boolean disableChekName;
	private boolean disableChekRemarks;
	private boolean disableChekOrderno;
	private boolean disableChekEffectiveDate;
	
	public SkillCheckListBean()
	{
		//this.formMode = "CREATE";
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
		
	}
	public SkillCheckListBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public SkillCheckListBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableChekKeyid = this.disableChekSkilKeyid =  this.disableChekName = this.disableChekRemarks = true;
			
		}
		else if(mode == FormModes.modify){
			//this.disableForm = false;
			this.formActionMode = FormModeConsts.modify;
		}
		else
			this.formActionMode = FormModeConsts.create;
		
	}
	
	private void lockFormControls(String lockFields){
		
		if( UIUtils.isValidKeyId(lockFields))
		{	
			String [] lockFieldNames = lockFields.split(",");
			for(String fieldName :lockFieldNames){
				
				if( fieldName.equals("KEYID")){
					this.disableChekKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableChekSkilKeyid = true;
				}
				else if( fieldName.equals("DESCRIPTION")) {
					this.disableChekName = true;
				}
				else if( fieldName.equals("DATE")) {
					this.disableChekRemarks = true;
				}
			}
		}
			
	}
	
	
	
	
	
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
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setDisableChekKeyid(boolean disableChekKeyid) {
		this.disableChekKeyid = disableChekKeyid;
	}
	public boolean isDisableChekKeyid() {
		return disableChekKeyid;
	}
	public void setDisableChekSkilKeyid(boolean disableChekSkilKeyid) {
		this.disableChekSkilKeyid = disableChekSkilKeyid;
	}
	public boolean isDisableChekSkilKeyid() {
		return disableChekSkilKeyid;
	}
	public void setDisableChekName(boolean disableChekName) {
		this.disableChekName = disableChekName;
	}
	public boolean isDisableChekName() {
		return disableChekName;
	}
	public void setDisableChekRemarks(boolean disableChekRemarks) {
		this.disableChekRemarks = disableChekRemarks;
	}
	public boolean isDisableChekRemarks() {
		return disableChekRemarks;
	}
	public void setDisableChekOrderno(boolean disableChekOrderno) {
		this.disableChekOrderno = disableChekOrderno;
	}
	public boolean isDisableChekOrderno() {
		return disableChekOrderno;
	}
	public void setDisableChekEffectiveDate(boolean disableChekEffectiveDate) {
		this.disableChekEffectiveDate = disableChekEffectiveDate;
	}
	public boolean isDisableChekEffectiveDate() {
		return disableChekEffectiveDate;
	}
	
}
