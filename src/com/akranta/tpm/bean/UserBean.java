package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class UserBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String chkIsdefPwd;
	private boolean disableUserKeyid;
	
	public UserBean()
	{
		this.formMode = "CREATE";
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public UserBean(FormModes mode)
	{
		
		if((mode == FormModes.modify))
		{
			
			this.setDisableUserKeyid(true);
			
		}
		else if((mode == FormModes.create))
		{
			
			this.setDisableUserKeyid(true);
		}
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
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
	public void setDisableUserKeyid(boolean disableUserKeyid) {
		
		this.disableUserKeyid = disableUserKeyid;
		
	}
	public boolean isDisableUserKeyid() {
		return disableUserKeyid;
	}
	public void setChkIsdefPwd(String chkIsdefPwd) {
		this.chkIsdefPwd = chkIsdefPwd;
	}
	public String getChkIsdefPwd() {
		return chkIsdefPwd;
	}
	
	
}
