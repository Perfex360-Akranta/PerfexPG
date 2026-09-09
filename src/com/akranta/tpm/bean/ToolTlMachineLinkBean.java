package com.akranta.tpm.bean;

import java.util.List;

public class ToolTlMachineLinkBean {	
	
	public String getToolId() {
		return toolId;
	}
	public void setToolId(String toolId) {
		this.toolId = toolId;
	}
	public String getMchKeyid() {
		return mchKeyid;
	}
	public void setMchKeyid(String mchKeyid) {
		this.mchKeyid = mchKeyid;
	}
	public String getMchShare() {
		return mchShare;
	}
	public void setMchShare(String mchShare) {
		this.mchShare = mchShare;
	}
	
	public String getMchStdLife() {
		return mchStdLife;
	}
	public void setMchStdLife(String mchStdLife) {
		this.mchStdLife = mchStdLife;
	}
	
	public String getMchOldQty() {
		return mchOldQty;
	}
	public void setMchOldQty(String mchOldQty) {
		this.mchOldQty = mchOldQty;
	}
	
	public List<ToolTlMachineLinkBean> getMachineLink() {
		return machineLink;
	}
	public void setMachineLink(List<ToolTlMachineLinkBean> machineLink) {
		this.machineLink = machineLink;
	}
	private String mchStdLife;
	private String mchOldQty;
	private String toolId;
	private String mchKeyid;
	private String mchShare;
	private List<ToolTlMachineLinkBean> machineLink;
}
