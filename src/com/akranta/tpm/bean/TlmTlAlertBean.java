package com.akranta.tpm.bean;

import java.util.List;

public class TlmTlAlertBean {
	private String toolShrpNo;
	private String keyId;
	private String toolSrlNo;
	private List<TlmTlAlertBean> toolAlert;
	
	public String getToolSrhpNo() {
		return toolShrpNo;
	}
	public void setToolShrpNo(String toolShrpNo) {
		this.toolShrpNo = toolShrpNo;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getToolSrlNo() {
		return toolSrlNo;
	}
	public void setToolSrlNo(String toolSrlNo) {
		this.toolSrlNo = toolSrlNo;
	}
	public List<TlmTlAlertBean> getToolAlert() {
		return toolAlert;
	}
	public void setToolAlert(List<TlmTlAlertBean> toolAlert) {
		this.toolAlert = toolAlert;
	}
	
}
