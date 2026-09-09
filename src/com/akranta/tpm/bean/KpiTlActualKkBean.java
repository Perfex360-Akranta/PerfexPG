package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class KpiTlActualKkBean 
{
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableForm;
	private String kaukKeyid;
	private String kaukIndicatorid;
	private String kaukFactoryid;
	private String kaukSectionid;
	private String kaukCellid;
	private String kaukPillarid;
	private String kaukCalendaryear;
	private String kaukMonthyear;
	private String kaukExcellencevalue;
	private String kaukBenchmarkvalue;
	private String kaukIsactual;
	private String kaukFreqtype;
	private String kaukStatus;
	private String kaukTempfield1;
	private String kaukTempfield2;
	private String kaukTempfield3;
	private String kaukActive;
	private String kaukCreatedby;
	private String kaukCreatedon;
	private String kaukModifiedon;
	
	public KpiTlActualKkBean()
	{
			
	}
	public KpiTlActualKkBean(FormModes mode)
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
	public String getKaukKeyid() {
		return this.kaukKeyid;
	}

	public void setKaukKeyid(String kaukKeyid) {
		this.kaukKeyid = kaukKeyid;
	}
	
	public String getKaukIndicatorid() {
		return this.kaukIndicatorid;
	}

	public void setKaukIndicatorid(String kaukIndicatorid) {
		this.kaukIndicatorid = kaukIndicatorid;
	}

	public String getKaukFactoryid() {
		return this.kaukFactoryid;
	}

	public void setKaukFactoryid(String kaukFactoryid) {
		this.kaukFactoryid = kaukFactoryid;
	}

	public String getKaukSectionid() {
		return this.kaukSectionid;
	}

	public void setKaukSectionid(String kaukSectionid) {
		this.kaukSectionid = kaukSectionid;
	}

	public String getKaukCellid() {
		return this.kaukCellid;
	}

	public void setKaukCellid(String kaukCellid) {
		this.kaukCellid = kaukCellid;
	}

	public String getKaukPillarid() {
		return this.kaukPillarid;
	}

	public void setKaukPillarid(String kaukPillarid) {
		this.kaukPillarid = kaukPillarid;
	}

	public String getKaukCalendaryear() {
		return this.kaukCalendaryear;
	}

	public void setKaukCalendaryear(String kaukCalendaryear) {
		this.kaukCalendaryear = kaukCalendaryear;
	}
	
	public String getKaukMonthyear() {
		return this.kaukMonthyear;
	}

	public void setKaukMonthyear(String kaukMonthyear) {
		this.kaukMonthyear = kaukMonthyear;
	}
		
	public String getKaukExcellencevalue() {
		return this.kaukExcellencevalue;
	}

	public void setKaukExcellencevalue(String kaukExcellencevalue) {
		this.kaukExcellencevalue = kaukExcellencevalue;
	}

	public String getKaukBenchmarkvalue() {
		return this.kaukBenchmarkvalue;
	}

	public void setKaukBenchmarkvalue(String kaukBenchmarkvalue) {
		this.kaukBenchmarkvalue = kaukBenchmarkvalue;
	}

	public String getKaukIsactual() {
		return this.kaukIsactual;
	}

	public void setKaukIsactual(String kaukIsactual) {
		this.kaukIsactual = kaukIsactual;
	}

	public String getKaukFreqtype() {
		return this.kaukFreqtype;
	}

	public void setKaukFreqtype(String kaukFreqtype) {
		this.kaukFreqtype = kaukFreqtype;
	}

	public String getKaukStatus() {
		return this.kaukStatus;
	}

	public void setKaukStatus(String kaukStatus) {
		this.kaukStatus = kaukStatus;
	}
	
	public String getKaukTempfield1() {
		return this.kaukTempfield1;
	}

	public void setKaukTempfield1(String kaukTempfield1) {
		this.kaukTempfield1 = kaukTempfield1;
	}
	
	public String getKaukTempfield2() {
		return this.kaukTempfield2;
	}

	public void setKaukTempfield2(String kaukTempfield2) {
		this.kaukTempfield2 = kaukTempfield2;
	}
	
	public String getKaukTempfield3() {
		return this.kaukTempfield3;
	}

	public void setKaukTempfield3(String kaukTempfield3) {
		this.kaukTempfield3 = kaukTempfield3;
	}
	
	public String getKaukActive() {
		return this.kaukActive;
	}

	public void setKaukActive(String kaukActive) {
		this.kaukActive = kaukActive;
	}

	public String getKaukCreatedby() {
		return this.kaukCreatedby;
	}

	public void setKaukCreatedby(String kaukCreatedby) {
		this.kaukCreatedby = kaukCreatedby;
	}

	public String getKaukCreatedon() {
		return this.kaukCreatedon;
	}

	public void setKaukCreatedon(String kaukCreatedon) {
		this.kaukCreatedon = kaukCreatedon;
	}

	public String getKaukModifiedon() {
		return this.kaukModifiedon;
	}

	public void setKaukModifiedon(String kaukModifiedon) {
		this.kaukModifiedon = kaukModifiedon;
	}		
}
