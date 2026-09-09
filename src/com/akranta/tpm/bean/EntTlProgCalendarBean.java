package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class EntTlProgCalendarBean 
{
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableForm;
	private String ecalKeyid;
	private String ecalProgId;
	private String ecalPlanFromdate;
	private String ecalPlanTilldate;
	private String ecalPlanMonth;
	private String ecalPlanWeek;
	private String ecalPlanAudiencecount;
	private String ecalRequestby;
	private String ecalRequestdate;
	private String ecalRequestremarks;
	private String ecalApprovedflag;
	private String ecalApprovedby;
	private String ecalApproveddate;
	private String ecalApprovedremarks;
	private String ecalActualFromdate;
	private String ecalActualTilldate;
	private String ecalActualAudiencecount;
	private String ecalStatus;
	private String ecalMonthwise;
	private String ecalTempfield1;
	private String ecalTempfield2;
	private String ecalTempfield3;
	private String ecalTempfield4;
	private String ecalTempfield5;
	private String ecalActive;
	private String ecalCreatedby;
	private String ecalCreatedon;
	private String ecalModifiedon;
	
	public EntTlProgCalendarBean()
	{
			
	}
	public EntTlProgCalendarBean(FormModes mode)
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
	public String getEcalKeyid() {
		return this.ecalKeyid;
	}

	public void setEcalKeyid(String ecalKeyid) {
		this.ecalKeyid = ecalKeyid;
	}

	public String getEcalProgId() {
		return this.ecalProgId;
	}

	public void setEcalProgId(String ecalProgId) {
		this.ecalProgId = ecalProgId;
	}

	public String getEcalPlanFromdate() {
		return this.ecalPlanFromdate;
	}

	public void setEcalPlanFromdate(String ecalPlanFromdate) {
		this.ecalPlanFromdate = ecalPlanFromdate;
	}

	public String getEcalPlanTilldate() {
		return this.ecalPlanTilldate;
	}

	public void setEcalPlanTilldate(String ecalPlanTilldate) {
		this.ecalPlanTilldate = ecalPlanTilldate;
	}

	public String getEcalPlanMonth() {
		return this.ecalPlanMonth;
	}

	public void setEcalPlanMonth(String ecalPlanMonth) {
		this.ecalPlanMonth = ecalPlanMonth;
	}

	public String getEcalPlanWeek() {
		return this.ecalPlanWeek;
	}

	public void setEcalPlanWeek(String ecalPlanWeek) {
		this.ecalPlanWeek = ecalPlanWeek;
	}

	public String getEcalPlanAudiencecount() {
		return this.ecalPlanAudiencecount;
	}

	public void setEcalPlanAudiencecount(String ecalPlanAudiencecount) {
		this.ecalPlanAudiencecount = ecalPlanAudiencecount;
	}

	public String getEcalRequestby() {
		return this.ecalRequestby;
	}

	public void setEcalRequestby(String ecalRequestby) {
		this.ecalRequestby = ecalRequestby;
	}
	
	public String getEcalRequestdate() {
		return this.ecalRequestdate;
	}

	public void setEcalRequestdate(String ecalRequestdate) {
		this.ecalRequestdate = ecalRequestdate;
	}

	public String getEcalRequestremarks() {
		return this.ecalRequestremarks;
	}

	public void setEcalRequestremarks(String ecalRequestremarks) {
		this.ecalRequestremarks = ecalRequestremarks;
	}

	public String getEcalApprovedflag() {
		return this.ecalApprovedflag;
	}

	public void setEcalApprovedflag(String ecalApprovedflag) {
		this.ecalApprovedflag = ecalApprovedflag;
	}

	public String getEcalApprovedby() {
		return this.ecalApprovedby;
	}

	public void setEcalApprovedby(String ecalApprovedby) {
		this.ecalApprovedby = ecalApprovedby;
	}

	public String getEcalApproveddate() {
		return this.ecalApproveddate;
	}

	public void setEcalApproveddate(String ecalApproveddate) {
		this.ecalApproveddate = ecalApproveddate;
	}

	public String getEcalApprovedremarks() {
		return this.ecalApprovedremarks;
	}

	public void setEcalApprovedremarks(String ecalApprovedremarks) {
		this.ecalApprovedremarks = ecalApprovedremarks;
	}

	public String getEcalActualFromdate() {
		return this.ecalActualFromdate;
	}

	public void setEcalActualFromdate(String ecalActualFromdate) {
		this.ecalActualFromdate = ecalActualFromdate;
	}

	public String getEcalActualTilldate() {
		return this.ecalActualTilldate;
	}
	
	public void setEcalActualTilldate(String ecalActualTilldate) {
		this.ecalActualTilldate = ecalActualTilldate;
	}

	public String getEcalActualAudiencecount() {
		return this.ecalActualAudiencecount;
	}

	public void setEcalActualAudiencecount(String ecalActualAudiencecount) {
		this.ecalActualAudiencecount = ecalActualAudiencecount;
	}
	
	public String getEcalStatus() {
		return this.ecalStatus;
	}

	public void setEcalStatus(String ecalStatus) {
		this.ecalStatus = ecalStatus;
	}

	public String getEcalMonthwise() {
		return this.ecalMonthwise;
	}

	public void setEcalMonthwise(String ecalMonthwise) {
		this.ecalMonthwise = ecalMonthwise;
	}

	public String getEcalTempfield1() {
		return this.ecalTempfield1;
	}

	public void setEcalTempfield1(String ecalTempfield1) {
		this.ecalTempfield1 = ecalTempfield1;
	}

	public String getEcalTempfield2() {
		return this.ecalTempfield2;
	}

	public void setEcalTempfield2(String ecalTempfield2) {
		this.ecalTempfield2 = ecalTempfield2;
	}

	public String getEcalTempfield3() {
		return this.ecalTempfield3;
	}

	public void setEcalTempfield3(String ecalTempfield3) {
		this.ecalTempfield3 = ecalTempfield3;
	}

	public String getEcalTempfield4() {
		return this.ecalTempfield4;
	}

	public void setEcalTempfield4(String ecalTempfield4) {
		this.ecalTempfield4 = ecalTempfield4;
	}

	public String getEcalTempfield5() {
		return this.ecalTempfield5;
	}

	public void setEcalTempfield5(String ecalTempfield5) {
		this.ecalTempfield5 = ecalTempfield5;
	}

	public String getEcalActive() {
		return this.ecalActive;
	}

	public void setEcalActive(String ecalActive) {
		this.ecalActive = ecalActive;
	}

	public String getEcalCreatedby() {
		return this.ecalCreatedby;
	}

	public void setEcalCreatedby(String ecalCreatedby) {
		this.ecalCreatedby = ecalCreatedby;
	}

	public String getEcalCreatedon() {
		return this.ecalCreatedon;
	}

	public void setEcalCreatedon(String ecalCreatedon) {
		this.ecalCreatedon = ecalCreatedon;
	}

	public String getEcalModifiedon() {
		return this.ecalModifiedon;
	}

	public void setEcalModifiedon(String ecalModifiedon) {
		this.ecalModifiedon = ecalModifiedon;
	}		
}
