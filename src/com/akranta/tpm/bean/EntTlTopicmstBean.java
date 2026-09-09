package com.akranta.tpm.bean;

import com.akranta.tpm.model.EntTlTopicmst.tableFldConstants;
import com.akranta.tpm.utils.FormModes;

public class EntTlTopicmstBean 
{
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableForm;
	
	private String topiKeyid;
	private String topiLocationid;
	private String topiCode;
	private String topiName;
	private String topiParentid;
	private String topiIschild;
	private String topiEvaluationtypeid;
	private String topiType;
	private String topiRemarks;
	private String topiEffectiveDate;	
	private String topiInactiveDate;
	private String topiTempfield1;	
	private String topiTempfield2;
	private String topiTempfield3;
	private String topiTempfield4;
	private String topiTempfield5;
	private String topiActive;
	private String topiCreatedby;
	private String topiCreatedon;	
	private String topiModifiedon;
	
	
	public EntTlTopicmstBean()
	{
			
	}
	public EntTlTopicmstBean(FormModes mode)
	{
		this.setFormMode(mode);
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
			this.setFormActionMode("Completed");
		}
		else if(mode == FormModes.view)
		{
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
	public String geTtopiKeyid() {
		return topiKeyid;
	}

	public void setTopiKeyid(String topiKeyid) {
		this.topiKeyid = topiKeyid;
	}

	public String getTopiLocationid() {
		return topiLocationid;
	}

	public void setTopiLocationid(String topiLocationid) {
		this.topiLocationid = topiLocationid;
	}

	public String getTopiCode() {
		return topiCode;
	}

	public void setTopiCode(String topiCode) {
		this.topiCode = topiCode;
	}

	public String getTopiName() {
		return topiName;
	}

	public void setTopiName(String topiName) {
		this.topiName = topiName;
	}

	public String getTopiParentid() {
		return topiParentid;
	}

	public void setTopiParentid(String topiParentid) {
		this.topiParentid = topiParentid;
	}

	public String getTopiIschild() {
		return topiIschild;
	}

	public void setTopiIschild(String topiIschild) {
		this.topiIschild= topiIschild;
	}
	
	public String getTopiEvaluationtypeid() {
		return topiEvaluationtypeid;
	}

	public void setTopiEvaluationtypeid(String topiEvaluationtypeid) {
		this.topiEvaluationtypeid= topiEvaluationtypeid;
	}
	
	public String getTopiType() {
		return topiType;
	}

	public void setTopiType(String topiType) {
		this.topiType= topiType;
	}
	
	public String getTopiRemarks() {
		return topiRemarks;
	}

	public void setTopiRemarks(String topiRemarks) {
		this.topiRemarks= topiRemarks;
	}
	
	public String getTopiEffectiveDate() {
		return topiEffectiveDate;
	}

	public void setTopiEffectiveDate(String topiEffectiveDate) {
		this.topiEffectiveDate = topiEffectiveDate;
	}

	public String getTopiInactiveDate() {
		return topiInactiveDate;
	}

	public void setTopiInactiveDate(String topiInactiveDate) {
		this.topiInactiveDate= topiInactiveDate;
	}

	public String getTopiTempfield1() {
		return topiTempfield1;
	}

	public void setTopiTempfield1(String topiTempfield1) {
		this.topiTempfield1= topiTempfield1;
	}

	public String getTopiTempfield2() {
		return topiTempfield2;
	}

	public void setTopiTempfield2(String topiTempfield2) {
		this.topiTempfield2= topiTempfield2;
	}

	public String getTopiTempfield3() {
		return topiTempfield3;
	}

	public void setTopiTempfield3(String topiTempfield3) {
		this.topiTempfield3 = topiTempfield3;
	}

	public String getTopiTempfield4() {
		return topiTempfield4;
	}

	public void setTopiTempfield4(String topiTempfield4) {
		this.topiTempfield4 = topiTempfield4;
	}

	public String getTopiTempfield5() {
		return topiTempfield5;
	}

	public void setTopiTempfield5(String topiTempfield5) {
		this.topiTempfield5 = topiTempfield5;
	}

	public String getTopiActive() {
		return topiActive;
	}

	public void setTopiActive(String topiActive) {
		this.topiActive = topiActive;
	}
	public String getTopiCreatedby() {
		return topiCreatedby;
	}
	public void setTopiCreatedby(String topiCreatedby) {
		this.topiCreatedby = topiCreatedby;
	}
	public String getTopiCreatedon() {
		return topiCreatedon;
	}

	public void setTopiCreatedon(String topiCreatedon) {
		this.topiCreatedon = topiCreatedon;
	}

	public String getTopiModifiedon() {
		return topiModifiedon;
	}

	public void setTopiModifiedon(String topiModifiedon) {
		this.topiModifiedon= topiModifiedon;
	}
}
