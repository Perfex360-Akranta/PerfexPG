package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;

public class GenTlHolidaymstBean {
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String responsibility;
	private String BeanYear;
	private String BeanDay;
	private String HolmKeyid;
	private boolean disableForm ;
	private boolean disableHolmKeyid  ;
	private boolean disableHolmName ;
	private boolean disableHolmDescription ;
	private boolean disableHolmDate;
	private boolean disableHolmFactory;
	private boolean disableHolmHolidayflag;
	private boolean disableHolmCreatedby;
	private String actionmode;
	
	
	public GenTlHolidaymstBean()
	{
		//this.formMode = "CREATE";
		this.formMode = FormModes.create;
		this.formActionMode = FormModeConsts.create;
		this.setDisableForm(false);
		
	}
	public GenTlHolidaymstBean(FormModes mode)
	{
		formModeSettings(mode);
		

	}
	
	public GenTlHolidaymstBean(FormModes mode, String lockFields){
		formModeSettings(mode);
		lockFormControls(lockFields);
	}
	
	private void formModeSettings(FormModes mode){
		this.formMode = mode;
		if( mode == FormModes.view ){
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableHolmKeyid = this.disableHolmName =  this.disableHolmDescription = this.disableHolmDate = true;
			this.setDisableHolmFactory(this.setDisableHolmHolidayflag(this.setDisableHolmCreatedby(true)));
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
					this.disableHolmKeyid = true;
				}
				else if( fieldName.equals("NAME")) {
					this.disableHolmName = true;
				}
				else if( fieldName.equals("DESCRIPTION")) {
					this.disableHolmDescription = true;
				}
				else if( fieldName.equals("DATE")) {
					this.disableHolmDate = true;
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
	
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}

	public String getFormHeader() {
		return formHeader;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	
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
	public void setDisableHolmKeyid(boolean disableHolmKeyid) {
		this.disableHolmKeyid = disableHolmKeyid;
	}
	public boolean isDisableHolmKeyid() {
		return disableHolmKeyid;
	}
	public boolean setDisableHolmName(boolean disableHolmName) {
		this.disableHolmName = disableHolmName;
		return disableHolmName;
	}
	public boolean isDisableHolmName() {
		return disableHolmName;
	}
	public boolean setDisableHolmDescription(boolean disableHolmDescription) {
		this.disableHolmDescription = disableHolmDescription;
		return disableHolmDescription;
	}
	public boolean isDisableHolmDescription() {
		return disableHolmDescription;
	}
	public boolean setDisableHolmDate(boolean disableHolmDate) {
		this.disableHolmDate = disableHolmDate;
		return disableHolmDate;
	}
	public boolean isDisableHolmDate() {
		return disableHolmDate;
	}
	public void setActionmode(String actionmode) {
		this.actionmode = actionmode;
	}
	public String getActionmode() {
		return actionmode;
	}
	public void setDisableHolmFactory(boolean disableHolmFactory) {
		this.disableHolmFactory = disableHolmFactory;
	}
	public boolean isDisableHolmFactory() {
		return disableHolmFactory;
	}
	public boolean setDisableHolmHolidayflag(boolean disableHolmHolidayflag) {
		this.disableHolmHolidayflag = disableHolmHolidayflag;
		return disableHolmHolidayflag;
	}
	public boolean isDisableHolmHolidayflag() {
		return disableHolmHolidayflag;
	}
	public boolean setDisableHolmCreatedby(boolean disableHolmCreatedby) {
		this.disableHolmCreatedby = disableHolmCreatedby;
		return disableHolmCreatedby;
	}
	public boolean isDisableHolmCreatedby() {
		return disableHolmCreatedby;
	}
	public void setHolmKeyid(String holmKeyid) {
		HolmKeyid = holmKeyid;
	}
	public String getHolmKeyid() {
		return HolmKeyid;
	}
	

	
	public String getBeanYear() {
		return BeanYear;
	}
	public void setBeanYear(String beanYear) {
		BeanYear = beanYear;
	}
	public String getBeanDay() {
		return BeanDay;
	}
	public void setBeanDay(String beanDay) {
		BeanDay = beanDay;
	}
}
