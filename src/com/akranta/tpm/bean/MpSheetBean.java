package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class MpSheetBean  {

    private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableabnmKeyid;
	private boolean disableForm;
	private boolean disableComptdDtls;
	private String beforeImage;
	private String afterImage;
	private String resultImage;
	private boolean disableMpsKeyid;
	private String hdRequiredY;
	private String hdRequiredN;
	private String sectionId;
	private String factoryId;
	public MpSheetBean(){
		
	}
	public MpSheetBean(FormModes mode)
	{
		this.formMode = mode;
		this.setDisableForm(false);
		if(mode == FormModes.create )
		{	
			this.setDisableForm(false);
			this.setFormActionMode("Create");
		}
		else if( mode == FormModes.modify)
		{
			this.setDisableForm(false);
			this.setFormActionMode("Modify");
			
		}
		else if(mode == FormModes.completion)
		{
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("Completed");
		}
		else if(mode == FormModes.view)
		{
			this.setDisableForm(true);
			this.setDisableComptdDtls(true);
			this.setFormActionMode("View");
		}
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode( FormModes formMode) {
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
	
	/**
	 * @param disableabnmKeyid the disableabnmKeyid to set
	 */
	public void setDisableabnmKeyid(boolean disableabnmKeyid) {
		this.disableabnmKeyid = disableabnmKeyid;
	}
	/**
	 * @return the disableabnmKeyid
	 */
	public boolean isDisableabnmKeyid() {
		return disableabnmKeyid;
	}
	/**
	 * @param disableForm the disableForm to set
	 */
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	/**
	 * @return the disableForm
	 */
	public boolean isDisableForm() {
		return disableForm;
	}
	/**
	 * @param disableComptdDtls the disableComptdDtls to set
	 */
	public void setDisableComptdDtls(boolean disableComptdDtls) {
		this.disableComptdDtls = disableComptdDtls;
	}
	/**
	 * @return the disableComptdDtls
	 */
	public boolean isDisableComptdDtls() {
		return disableComptdDtls;
	}
	/**
	 * @param tagStatus the tagStatus to set
	 */
	public void setAfterImage(String afterImage) {
		this.afterImage = afterImage;
	}
	public String getAfterImage() {
		return afterImage;
	}
	public void setResultImage(String resultImage) {
		this.resultImage = resultImage;
	}
	public String getResultImage() {
		return resultImage;
	}
	public void setDisableMpsKeyid(boolean disableMpsKeyid) {
		this.disableMpsKeyid = disableMpsKeyid;
	}
	public boolean isDisableMpsKeyid() {
		return disableMpsKeyid;
	}
	public void setBeforeImage(String beforeImage) {
		this.beforeImage = beforeImage;
	}
	public String getBeforeImage() {
		return beforeImage;
	}
	public void setHdRequiredY(String hdRequiredY) {
		this.hdRequiredY = hdRequiredY;
	}
	public String getHdRequiredY() {
		return hdRequiredY;
	}
	public void setHdRequiredN(String hdRequiredN) {
		this.hdRequiredN = hdRequiredN;
	}
	public String getHdRequiredN() {
		return hdRequiredN;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getFactoryId() {
		return factoryId;
	}
	
	

}
