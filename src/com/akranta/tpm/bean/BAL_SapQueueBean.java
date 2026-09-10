package com.akranta.tpm.bean;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_SapQueueBean {

	private String txtTransId;
	private String txtProcessCode;
	private String txtProcessName;
	private String txtStatus;
	private String txtMessage;
	private String btnAction;
	
	
	public String getTxtTransId() {
		return txtTransId;
	}
	public void setTxtTransId(String txtTransId) {
		this.txtTransId = txtTransId;
	}
	public String getTxtProcessCode() {
		return txtProcessCode;
	}
	public void setTxtProcessCode(String txtProcessCode) {
		this.txtProcessCode = txtProcessCode;
	}
	public String getTxtProcessName() {
		return txtProcessName;
	}
	public void setTxtProcessName(String txtProcessName) {
		this.txtProcessName = txtProcessName;
	}
	public String getTxtStatus() {
		return txtStatus;
	}
	public void setTxtStatus(String txtStatus) {
		this.txtStatus = txtStatus;
	}
	public String getTxtMessage() {
		return txtMessage;
	}
	public void setTxtMessage(String txtMessage) {
		this.txtMessage = txtMessage;
	}
	public String getBtnAction() {
		return btnAction;
	}
	public void setBtnAction(String btnAction) {
		this.btnAction = btnAction;
	}
	
}
