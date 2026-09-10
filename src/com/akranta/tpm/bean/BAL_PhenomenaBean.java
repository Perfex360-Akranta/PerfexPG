package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class BAL_PhenomenaBean {
	
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;	
	private boolean disableForm ;
	private boolean disableBphmKeyid  ;
	private boolean disableBphmAssemblyid ;
	private boolean disableBphmPhenomenaname ;
	private boolean disableBphmRemarks;
	private String actionmode;
	
	
	public BAL_PhenomenaBean()
	{
		//this.formMode = "CREATE";
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
		
	}
	public BAL_PhenomenaBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public BAL_PhenomenaBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableBphmKeyid = this.disableBphmAssemblyid =  this.disableBphmPhenomenaname = this.disableBphmRemarks = true;
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
					this.disableBphmKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableBphmPhenomenaname = true;
				}
				else if( fieldName.equals("ASSEMBLY")) {
					this.disableBphmAssemblyid = true;
				}
				else if( fieldName.equals("REMARKS")) {
					this.disableBphmRemarks = true;
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
	public boolean isDisableBphmKeyid() {
		return disableBphmKeyid;
	}
	public void setDisableBphmKeyid(boolean disableBphmKeyid) {
		this.disableBphmKeyid = disableBphmKeyid;
	}
	public boolean isDisableBphmAssemblyid() {
		return disableBphmAssemblyid;
	}
	public void setDisableBphmAssemblyid(boolean disableBphmAssemblyid) {
		this.disableBphmAssemblyid = disableBphmAssemblyid;
	}
	public boolean isDisableBphmPhenomenaname() {
		return disableBphmPhenomenaname;
	}
	public void setDisableBphmPhenomenaname(boolean disableBphmPhenomenaname) {
		this.disableBphmPhenomenaname = disableBphmPhenomenaname;
	}
	public boolean isDisableBphmRemarks() {
		return disableBphmRemarks;
	}
	public void setDisableBphmRemarks(boolean disableBphmRemarks) {
		this.disableBphmRemarks = disableBphmRemarks;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}

}
