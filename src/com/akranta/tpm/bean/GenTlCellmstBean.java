package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GenTlCellmstBean 
{
	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableBtnExcelView;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	private boolean disableForm ;
	private boolean disableCellKeyid ;
	private boolean disableCellCompanyid ;
	private boolean disableCellFactoryid;
	private boolean disableCellSectionid;
	private boolean disableCellCostcentreid;
	private boolean disableCellName;
	private boolean disableCellCode;
	private boolean disableCellEffectivedate;
	private String actionmode;
	public GenTlCellmstBean()
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
	public GenTlCellmstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	public GenTlCellmstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.setDisableForm(true);
			this.formActionMode = FormModeConsts.view;
			this.disableCellKeyid =this.disableCellCompanyid=this.disableCellFactoryid=this.disableCellSectionid=this.disableCellCostcentreid=this.disableCellName=this.disableCellCode=this.disableCellEffectivedate=true;
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
					this.disableCellKeyid= true;
				}
				else if( fieldName.equals("COMPANY")) {
					this.disableCellCompanyid= true;
				}
				else if( fieldName.equals("FACTORY")) {
					this.disableCellFactoryid=true;
				}
				else if( fieldName.equals("SECTION")) {
					this.disableCellSectionid= true;;
				}
				else if( fieldName.equals("COSTCENTER")) {
					this.disableCellCostcentreid= true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableCellName= true;
				}
				else if( fieldName.equals("CODE")) {
					this.disableCellCode= true;
				}
				else if( fieldName.equals("EFFECTIVEDATE")) {
					this.disableCellEffectivedate= true;
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
		public void setDisableCellKeyid(boolean disableCellKeyid) {
			this.disableCellKeyid = disableCellKeyid;
		}
		public boolean isDisableCellKeyid() {
			return disableCellKeyid;
		}
		public void setDisableCellCompanyid(boolean disableCellCompanyid) {
			this.disableCellCompanyid = disableCellCompanyid;
		}
		public boolean isDisableCellCompanyid() {
			return disableCellCompanyid;
		}
		public void setDisableCellFactoryid(boolean disableCellFactoryid) {
			this.disableCellFactoryid = disableCellFactoryid;
		}
		public boolean isDisableCellFactoryid() {
			return disableCellFactoryid;
		}
		public void setDisableCellSectionid(boolean disableCellSectionid) {
			this.disableCellSectionid = disableCellSectionid;
		}
		public boolean isDisableCellSectionid() {
			return disableCellSectionid;
		}
		public void setDisableCellCostcentreid(boolean disableCellCostcentreid) {
			this.disableCellCostcentreid = disableCellCostcentreid;
		}
		public boolean isDisableCellCostcentreid() {
			return disableCellCostcentreid;
		}
		public void setDisableCellName(boolean disableCellName) {
			this.disableCellName = disableCellName;
		}
		public boolean isDisableCellName() {
			return disableCellName;
		}
		public void setDisableCellCode(boolean disableCellCode) {
			this.disableCellCode = disableCellCode;
		}
		public boolean isDisableCellCode() {
			return disableCellCode;
		}
		public void setDisableCellEffectivedate(boolean disableCellEffectivedate) {
			this.disableCellEffectivedate = disableCellEffectivedate;
		}
		public boolean isDisableCellEffectivedate() {
			return disableCellEffectivedate;
		}
		public void setActionmode(String actionmode) {
			this.actionmode = actionmode;
		}
		public String getActionmode() {
			return actionmode;
		}
	}



