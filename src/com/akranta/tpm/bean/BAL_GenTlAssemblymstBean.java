package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class BAL_GenTlAssemblymstBean {
	@Override
	public String toString() {
		return "BAL_GenTlAssemblymstBean [formActionMode=" + formActionMode + ", formMode=" + formMode + ", formHeader="
				+ formHeader + ", responsibility=" + responsibility + ", machineId=" + machineId + ", refreshFlag="
				+ refreshFlag + ", disableForm=" + disableForm + ", disableAssmKeyid=" + disableAssmKeyid
				+ ", disableAssmName=" + disableAssmName + ", disableAssmDescription=" + disableAssmDescription
				+ ", disableAssmRemarks=" + disableAssmRemarks + ", actionmode=" + actionmode + "]";
	}
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String responsibility;
	private String machineId;
	private String refreshFlag;
	
	private boolean disableForm ;
	private boolean disableAssmKeyid  ;
	private boolean disableAssmName ;
	private boolean disableAssmDescription ;
	private boolean disableAssmRemarks;
	private String actionmode;
	
	
	public BAL_GenTlAssemblymstBean()
	{
		//this.formMode = "CREATE";
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
		
	}
	public BAL_GenTlAssemblymstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public BAL_GenTlAssemblymstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableAssmKeyid = this.disableAssmName =  this.disableAssmDescription = this.disableAssmRemarks = true;
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
					this.disableAssmKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableAssmName = true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableAssmDescription = true;
				}
				else if( fieldName.equals("ADDRESS")) {
					this.disableAssmRemarks = true;
				}
			}
		}
			
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
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	/**
	 * @return the formMode
	 */
	public FormModes getFormMode() {
		return formMode;
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
	public void setDisableAssmKeyid(boolean disableAssmKeyid) {
		this.disableAssmKeyid = disableAssmKeyid;
	}
	public boolean isDisableAssmKeyid() {
		return disableAssmKeyid;
	}
	public boolean setDisableAssmName(boolean disableAssmName) {
		this.disableAssmName = disableAssmName;
		return disableAssmName;
	}
	public boolean isDisableAssmName() {
		return disableAssmName;
	}
	public boolean setDisableAssmDescription(boolean disableAssmDescription) {
		this.disableAssmDescription = disableAssmDescription;
		return disableAssmDescription;
	}
	public boolean isDisableAssmDescription() {
		return disableAssmDescription;
	}
	public boolean setDisableAssmRemarks(boolean disableAssmRemarks) {
		this.disableAssmRemarks = disableAssmRemarks;
		return disableAssmRemarks;
	}
	public boolean isDisableAssmRemarks() {
		return disableAssmRemarks;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getRefreshFlag() {
		return refreshFlag;
	}
	public void setRefreshFlag(String refreshFlag) {
		this.refreshFlag = refreshFlag;
	}
}
