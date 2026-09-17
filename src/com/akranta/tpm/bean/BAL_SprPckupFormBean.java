
/*Created By : Siddharth.A*/
package com.akranta.tpm.bean;

public class BAL_SprPckupFormBean {

	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String cmbSprName;
	private String txtRequiredQty;
	private String createdBy;
	private String standardId;
	private String fnLnParentId;
	private String fnLnOriginalId;
	
	public BAL_SprPckupFormBean()
	{
		
	}
	/**
	 * @param formMode the formMode to set
	 */
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

	/**
	 * @param formMode the formMode to set
	 */
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	/**
	 * @return the formMode
	 */
	public String getFormMode() {
		return formMode;
	}
	/**
	 * @param cmbSprName the cmbSprName to set
	 */
	public void setCmbSprName(String cmbSprName) {
		this.cmbSprName = cmbSprName;
	}
	/**
	 * @return the cmbSprName
	 */
	public String getCmbSprName() {
		return cmbSprName;
	}
	/**
	 * @param txtRequiredQty the txtRequiredQty to set
	 */
	public void setTxtRequiredQty(String txtRequiredQty) {
		this.txtRequiredQty = txtRequiredQty;
	}
	/**
	 * @return the txtRequiredQty
	 */
	public String getTxtRequiredQty() {
		return txtRequiredQty;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param standardId the standardId to set
	 */
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	/**
	 * @return the standardId
	 */
	public String getStandardId() {
		return standardId;
	}
	
	/**
	 * @param fnLnParentId the fnLnParentId to set
	 */
	public void setFnLnParentId(String fnLnParentId) {
		this.fnLnParentId = fnLnParentId;
	}
	/**
	 * @return the fnLnParentId
	 */
	public String getFnLnParentId() {
		return fnLnParentId;
	}
	/**
	 * @param fnLnOriginalId the fnLnOriginalId to set
	 */
	public void setFnLnOriginalId(String fnLnOriginalId) {
		this.fnLnOriginalId = fnLnOriginalId;
	}
	/**
	 * @return the fnLnOriginalId
	 */
	public String getFnLnOriginalId() {
		return fnLnOriginalId;
	}
}
