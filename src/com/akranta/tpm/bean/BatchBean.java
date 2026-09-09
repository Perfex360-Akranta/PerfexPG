package com.akranta.tpm.bean;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.FormModes;

public class BatchBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String BachFromdate;
	private String BachTilldate;
	private boolean disableBachKeyid;
	private String displayEntCode;
	public BatchBean()
	{
		this.formMode = "CREATE";
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public BatchBean(FormModes mode)
	{
		if(mode == FormModes.modify)
		{
			this.setDisableBachKeyid(true);
			
		}
/*		else if(mode.equals("edit"))
		{
			this.setFormMode("edit");
			this.disableBachKeyid=true;
		}
*/		
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
		CommonMessage.debugMsg("mode set");
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setBachFromdate(String BachFromdate) {
		this.BachFromdate = BachFromdate;
	}
	public String getBachFromdate() {
		return BachFromdate;
	}
	public void setBachTilldate(String BachTilldate) {
		this.BachTilldate = BachTilldate;
	}
	public String getBachTilldate() {
		return BachTilldate;
	}
	public void setDisableBachKeyid(boolean disableBachKeyid) {
		this.disableBachKeyid = disableBachKeyid;
	}
	public boolean isDisableBachKeyid() {
		return disableBachKeyid;
	}
	public void setDisplayEntCode(String displayEntCode) {
		this.displayEntCode = displayEntCode;
	}
	public String getDisplayEntCode() {
		return displayEntCode;
	}
	
}
