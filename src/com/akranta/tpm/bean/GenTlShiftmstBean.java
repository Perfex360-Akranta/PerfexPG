
package com.akranta.tpm.bean;

public class GenTlShiftmstBean 
{
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private boolean disableBtnExcelView;
	private boolean disableChkApprovedBy;
	private boolean disableCmbApprovedBy;
	private boolean disableDteApprovedDate;
	public GenTlShiftmstBean()
	{
	
			this.formMode = "CREATE";
			this.disableBtnExcelView = true;
			this.disableChkApprovedBy= true;
			this.disableCmbApprovedBy= true;
			this.disableDteApprovedDate = true; 
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
		public  String getFormActionMode() {
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
		public void setFormMode(String formMode) {
			this.formMode = formMode;
		}
		/**
		 * @return the formMode
		 */
		public String getFormMode() {
			return formMode;
		}
	}



