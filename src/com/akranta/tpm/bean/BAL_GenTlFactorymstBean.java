package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class BAL_GenTlFactorymstBean {

	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableBtnExcelView;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableForm ;
	private boolean disableFactKeyid ;
	private boolean disableFactCompanyid ;
	private boolean disableFactLocationid;
	private boolean disableFactName;
	private boolean disableFactCode;
	private boolean disableFactAddress;
	
	private String actionmode;
	public BAL_GenTlFactorymstBean()
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
	public BAL_GenTlFactorymstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	public BAL_GenTlFactorymstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.setDisableForm(true);
			this.formActionMode = FormModeConsts.view;
			this.disableFactKeyid=this.disableFactCompanyid=this.disableFactLocationid=this.disableFactName=this.disableFactCode=this.disableFactAddress=true;
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
					this.disableFactKeyid=true;
				}
				else if( fieldName.equals("COMPANY")) {
					this.disableFactCompanyid=true;
				}
				else if( fieldName.equals("LOCATION")) {
					this.disableFactLocationid=true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableFactName = true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableFactCode=true;
				}
				else if( fieldName.equals("ADDRESS")) {
					this.disableFactAddress=true;
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
	public void setDisableFactKeyid(boolean disableFactKeyid) {
		this.disableFactKeyid = disableFactKeyid;
	}
	public boolean isDisableFactKeyid() {
		return disableFactKeyid;
	}
	public boolean setDisableFactCompanyid(boolean disableFactCompanyid) {
		this.disableFactCompanyid = disableFactCompanyid;
		return disableFactCompanyid;
	}
	public boolean isDisableFactCompanyid() {
		return disableFactCompanyid;
	}
	public boolean setDisableFactLocationid(boolean disableFactLocationid) {
		this.disableFactLocationid = disableFactLocationid;
		return disableFactLocationid;
	}
	public boolean isDisableFactLocationid() {
		return disableFactLocationid;
	}
	public boolean setDisableFactName(boolean disableFactName) {
		this.disableFactName = disableFactName;
		return disableFactName;
	}
	public boolean isDisableFactName() {
		return disableFactName;
	}
	public boolean setDisableFactCode(boolean disableFactCode) {
		this.disableFactCode = disableFactCode;
		return disableFactCode;
	}
	public boolean isDisableFactCode() {
		return disableFactCode;
	}
	public boolean setDisableFactAddress(boolean disableFactAddress) {
		this.disableFactAddress = disableFactAddress;
		return disableFactAddress;
	}
	public boolean isDisableFactAddress() {
		return disableFactAddress;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	
}
