package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class BAL_CauseBean {
	
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;	
	private boolean disableForm ;
	private boolean disableBcsmKeyid  ;
	private boolean disableBcsmPhenomenaid ;
	private boolean disableBcsmName ;
	private boolean disableBcsmRemarks;
	private String actionmode;
	
	
	public BAL_CauseBean()
	{
		//this.formMode = "CREATE";
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
		
	}
	public BAL_CauseBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public BAL_CauseBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableBcsmKeyid = this.disableBcsmPhenomenaid =  this.disableBcsmName = this.disableBcsmRemarks = true;
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
					this.disableBcsmKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableBcsmName = true;
				}
				else if( fieldName.equals("PHENOMENA")) {
					this.disableBcsmPhenomenaid = true;
				}
				else if( fieldName.equals("REMARKS")) {
					this.disableBcsmRemarks = true;
				}
			}
		}
			
	}
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
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableBcsmKeyid() {
		return disableBcsmKeyid;
	}
	public void setDisableBcsmKeyid(boolean disableBcsmKeyid) {
		this.disableBcsmKeyid = disableBcsmKeyid;
	}
	public boolean isDisableBcsmPhenomenaid() {
		return disableBcsmPhenomenaid;
	}
	public void setDisableBcsmPhenomenaid(boolean disableBcsmPhenomenaid) {
		this.disableBcsmPhenomenaid = disableBcsmPhenomenaid;
	}
	public boolean isDisableBcsmName() {
		return disableBcsmName;
	}
	public void setDisableBcsmName(boolean disableBcsmName) {
		this.disableBcsmName = disableBcsmName;
	}
	public boolean isDisableBcsmRemarks() {
		return disableBcsmRemarks;
	}
	public void setDisableBcsmRemarks(boolean disableBcsmRemarks) {
		this.disableBcsmRemarks = disableBcsmRemarks;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}

}
