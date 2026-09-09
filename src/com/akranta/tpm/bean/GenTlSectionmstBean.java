package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GenTlSectionmstBean {
	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableBtnExcelView;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableForm ;
	private boolean disableSectKeyid ;
	private boolean disableSectCompanyid ;
	private boolean disableSectFactoryid ;
	private boolean disableSectName;
	private boolean disableSectCode;
	private String actionmode;
	public GenTlSectionmstBean()
	{
	/*	this.formMode = "CREATE";
		this.disableBtnExcelView = true;
		this.disableChkApprovedBy= true;
		this.disableCmbApprovedBy= true;
		this.disableDteApprovedDate = true;*/
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
	}
	public GenTlSectionmstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public GenTlSectionmstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.setDisableForm(true);
			this.formActionMode = FormModeConsts.view;
			this.disableSectKeyid=this.disableSectCompanyid=this.disableSectFactoryid=this.disableSectName=this.disableSectCode=true;
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
					this.disableSectKeyid= true;
				}
				else if( fieldName.equals("COMPANY")) {
					this.disableSectCompanyid= true;
				}
				else if( fieldName.equals("FACTORY")) {
					this.disableSectFactoryid= true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableSectName= true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableSectCode= true;
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
	public void setDisableSectKeyid(boolean disableSectKeyid) {
		this.disableSectKeyid = disableSectKeyid;
	}
	public boolean isDisableSectKeyid() {
		return disableSectKeyid;
	}
	public boolean setDisableSectCompanyid(boolean disableSectCompanyid) {
		this.disableSectCompanyid = disableSectCompanyid;
		return disableSectCompanyid;
	}
	public boolean isDisableSectCompanyid() {
		return disableSectCompanyid;
	}
	public boolean setDisableSectFactoryid(boolean disableSectFactoryid) {
		this.disableSectFactoryid = disableSectFactoryid;
		return disableSectFactoryid;
	}
	public boolean isDisableSectFactoryid() {
		return disableSectFactoryid;
	}
	public boolean setDisableSectName(boolean disableSectName) {
		this.disableSectName = disableSectName;
		return disableSectName;
	}
	public boolean isDisableSectName() {
		return disableSectName;
	}
	public boolean setDisableSectCode(boolean disableSectCode) {
		this.disableSectCode = disableSectCode;
		return disableSectCode;
	}
	public boolean isDisableSectCode() {
		return disableSectCode;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}

}
