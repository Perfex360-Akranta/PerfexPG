
package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GenTlCompanymstBean {
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String responsibility;
	private boolean disableForm ;
	private boolean disableCompKeyid ;
	private boolean disableCompName ;
	private boolean disableCompCode ;
	private boolean disableCompAddress;
	private String actionmode;
	
	public GenTlCompanymstBean()
	{
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.disableForm = false;
	}
	
	public GenTlCompanymstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public GenTlCompanymstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableCompKeyid = this.disableCompName =  this.disableCompCode = this.disableCompAddress = true;
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
					this.disableCompKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableCompName = true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableCompCode = true;
				}
				else if( fieldName.equals("ADDRESS")) {
					this.disableCompAddress = true;
				}
			}
		}
			
	}
	
	
	public String getActionmode() {
		return actionmode;
	}
	
	public FormModes getFormMode() {
		return formMode;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}



	public void setFormActionMode(String formActionMode) {
		this.formActionMode= formActionMode;
	}
	
	public String getFormActionMode() {
		return formActionMode;
	}
	/**
	 * @param formHeader the formHeader to set
	 */
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	/**
	 * @return the formHeader
	 */
	public String getFormHeader() {
		return formHeader;
	}
	
	/**
	 * @return the formMode
	 */
	
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getResponsibility() {
		return responsibility;
	}


	public void setDisableCompKeyid(boolean disableCompKeyid) {
		this.disableCompKeyid = disableCompKeyid;
	}


	public boolean isDisableCompKeyid() {
		return disableCompKeyid;
	}


	public void setDisableCompName(boolean disableCompName) {
		this.disableCompName = disableCompName;
	}


	public boolean isDisableCompName() {
		return disableCompName;
	}


	public void setDisableCompCode(boolean disableCompCode) {
		this.disableCompCode = disableCompCode;
	}


	public boolean isDisableCompCode() {
		return disableCompCode;
	}

	public void setDisableCompAddress(boolean disableCompAddress) {
		this.disableCompAddress = disableCompAddress;
	}


	public boolean isDisableCompAddress() {
		return disableCompAddress;
	}
	
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
}

