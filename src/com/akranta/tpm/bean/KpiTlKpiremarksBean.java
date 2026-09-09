package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class KpiTlKpiremarksBean {
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableForm;

	private String kprmKeyid;
	private String kprmIndicatorid;
	private String kprmFlid;
	private String kprmDate;
	private String kprmRemarks;
	private String kprmTempfield1;
	private String kprmTempfield2;
	private String kprmTempfield3;
	private String kprmTempfield4;
	private String kprmTempfield5;
	private String kprmActive;
	private String kprmCreatedby;
	private String kprmCreatedon;
	private String kprmModifiedon;

	public KpiTlKpiremarksBean() {

	}

	public KpiTlKpiremarksBean(FormModes mode) {
		this.setFormMode(mode);
		this.setDisableForm(false);
		if (mode == FormModes.create) {
			this.setDisableForm(false);
			this.setFormActionMode("Create");
		} else if (mode == FormModes.modify) {
			this.setDisableForm(false);
			this.setFormActionMode("Modify");
		} else if (mode == FormModes.completion) {
			this.setDisableForm(true);
			this.setFormActionMode("Completed");
		} else if (mode == FormModes.view) {
			this.setDisableForm(true);
			this.setFormActionMode("View");
		}
	}

	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}

	public String getFormActionMode() {
		return formActionMode;
	}

	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}

	public FormModes getFormMode() {
		return formMode;
	}

	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}

	public boolean isDisableForm() {
		return disableForm;
	}

	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}

	public String getFormHeader() {
		return formHeader;
	}

	public String getKprmKeyid() {
		return kprmKeyid;
	}

	public void setKprmKeyid(String kprmKeyid) {
		this.kprmKeyid = kprmKeyid;
	}

	public String getKprmIndicatorid() {
		return kprmIndicatorid;
	}

	public void setKprmIndicatorid(String kprmIndicatorid) {
		this.kprmIndicatorid = kprmIndicatorid;
	}

	public String getKprmFlid() {
		return kprmFlid;
	}

	public void setKprmFlid(String kprmFlid) {
		this.kprmFlid = kprmFlid;
	}

	public String getKprmDate() {
		return kprmDate;
	}

	public void setKprmDate(String kprmDate) {
		this.kprmDate = kprmDate;
	}

	public String getKprmRemarks() {
		return kprmRemarks;
	}

	public void setKprmRemarks(String kprmRemarks) {
		this.kprmRemarks = kprmRemarks;
	}

	public String getKprmTempfield1() {
		return kprmTempfield1;
	}

	public void setKprmTempfield1(String kprmTempfield1) {
		this.kprmTempfield1 = kprmTempfield1;
	}

	public String getKprmTempfield2() {
		return kprmTempfield2;
	}

	public void setKprmTempfield2(String kprmTempfield2) {
		this.kprmTempfield2 = kprmTempfield2;
	}

	public String getKprmTempfield3() {
		return kprmTempfield3;
	}

	public void setKprmTempfield3(String kprmTempfield3) {
		this.kprmTempfield3 = kprmTempfield3;
	}

	public String getKprmTempfield4() {
		return kprmTempfield4;
	}

	public void setKprmTempfield4(String kprmTempfield4) {
		this.kprmTempfield4 = kprmTempfield4;
	}

	public String getKprmTempfield5() {
		return kprmTempfield5;
	}

	public void setKprmTempfield5(String kprmTempfield5) {
		this.kprmTempfield5 = kprmTempfield5;
	}

	public String getKprmActive() {
		return kprmActive;
	}

	public void setKprmActive(String kprmActive) {
		this.kprmActive = kprmActive;
	}

	public String getKprmCreatedby() {
		return kprmCreatedby;
	}

	public void setKprmCreatedby(String kprmCreatedby) {
		this.kprmCreatedby = kprmCreatedby;
	}

	public String getKprmCreatedon() {
		return kprmCreatedon;
	}

	public void setKprmCreatedon(String kprmCreatedon) {
		this.kprmCreatedon = kprmCreatedon;
	}

	public String getKprmModifiedon() {
		return kprmModifiedon;
	}

	public void setKprmModifiedon(String kprmModifiedon) {
		this.kprmModifiedon = kprmModifiedon;
	}

}
