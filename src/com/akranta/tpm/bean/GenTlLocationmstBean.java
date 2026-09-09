package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GenTlLocationmstBean {

	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableBtnExcelView;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableForm ;
	private boolean disableLocnKeyid ;
	private boolean disableLocnCompanyid ;
	private boolean disableLocnName ;
	private boolean disableLocnCode;
	private boolean disableLocnDescription;
	private String actionmode;
	public GenTlLocationmstBean()
	{
		/*this.formMode = "CREATE";
		this.disableBtnExcelView = true;
		this.disableChkApprovedBy= true;
		this.disableCmbApprovedBy= true;
		this.disableDteApprovedDate = true; */
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
	}
	public GenTlLocationmstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public GenTlLocationmstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableLocnKeyid = this.disableLocnCompanyid =  this.disableLocnName = this.disableLocnCode = this.disableLocnDescription = true;
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
					this.disableLocnKeyid = true;
				}
				else if( fieldName.equals("COMPANY")) {
					this.disableLocnCompanyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableLocnName = true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableLocnCode = true;
				}
				else if( fieldName.equals("DESCRIPTION")) {
					this.disableLocnDescription = true;
				}
			}
		}
			
	}
	public boolean isDisableBtnExcelView() {
		return disableBtnExcelView;
	}
	public void setDisableBtnExcelView(boolean disableBtnExcelView) {
		this.disableBtnExcelView = disableBtnExcelView;
	}
	public boolean isDisableChkApprovedBy() {
		return disableChkApprovedBy;
	}
	public void setDisableChkApprovedBy(boolean disableChkApprovedBy) {
		this.disableChkApprovedBy = disableChkApprovedBy;
	}
	public boolean isDisableCmbApprovedBy() {
		return disableCmbApprovedBy;
	}
	public void setDisableCmbApprovedBy(boolean disableCmbApprovedBy) {
		this.disableCmbApprovedBy = disableCmbApprovedBy;
	}
	public boolean isDisableDteApprovedDate() {
		return disableDteApprovedDate;
	}
	public void setDisableDteApprovedDate(boolean disableDteApprovedDate) {
		this.disableDteApprovedDate = disableDteApprovedDate;
	}
	
	
	
	public void setFormActionMode(String formActionMode) {
		this.formActionMode= formActionMode;
	}
	/**
	 * @return the formMode
	 */
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
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableLocnKeyid(boolean disableLocnKeyid) {
		this.disableLocnKeyid = disableLocnKeyid;
	}
	public boolean isDisableLocnKeyid() {
		return disableLocnKeyid;
	}
	public boolean setDisableLocnCompanyid(boolean disableLocnCompanyid) {
		this.disableLocnCompanyid = disableLocnCompanyid;
		return disableLocnCompanyid;
	}
	public boolean isDisableLocnCompanyid() {
		return disableLocnCompanyid;
	}
	public boolean setDisableLocnName(boolean disableLocnName) {
		this.disableLocnName = disableLocnName;
		return disableLocnName;
	}
	public boolean isDisableLocnName() {
		return disableLocnName;
	}
	public boolean setDisableLocnCode(boolean disableLocnCode) {
		this.disableLocnCode = disableLocnCode;
		return disableLocnCode;
	}
	public boolean isDisableLocnCode() {
		return disableLocnCode;
	}
	public boolean setDisableLocnDescription(boolean disableLocnDescription) {
		this.disableLocnDescription = disableLocnDescription;
		return disableLocnDescription;
	}
	public boolean isDisableLocnDescription() {
		return disableLocnDescription;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
}
