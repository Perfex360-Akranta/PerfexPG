package com.akranta.tpm.bean;

public class MOMeetingBean {
	

	private String formMode;
	
	private String MomdDisDtl;
	private String MomdType;
	private String MomCellId;
	
	public MOMeetingBean()
	{
		this.formMode = "CREATE";
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	
	public void setformMode(String formMode) {
		this.formMode = formMode;
	}
	public String getformMode() {
		return formMode;
	}
	public void setMomdDisDtl(String MomdDisDtl) {
		this.MomdDisDtl = MomdDisDtl;
	}
	public String getMomdDisDtl() {
		return MomdDisDtl;
	}
	public void setMomdType(String MomdType) {
		this.MomdType = MomdType;
	}
	public String getMomdDistl() {
		return MomdType;
	}

	public void setMomCellId(String momCellId) {
		MomCellId = momCellId;
	}

	public String getMomCellId() {
		return MomCellId;
	}

	

}
