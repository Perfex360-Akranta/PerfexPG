package com.akranta.tpm.bean;
import com.akranta.tpm.model.KpiTlIndicatorKk.tableFldConstants;
import com.akranta.tpm.utils.FormModes;

public class KpiTlIndicatorKkBean 
{
	private String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private boolean disableForm;
	private String kinkKeyid;
	private String kinkIndicatorname;
	private String kinkIndicatorcode;
	private String kinkDescription;
	private String kinkParentid;
	private String kinkLevelno;
	private String kinkSortno;
	private String kinkIschild;	
	private String kinkInputtype;
	private String kinkInputentry;
	private String kinkIdentifier;
	private String kinkManualcalctype;
	private String kinkUomid;
	private String kinkFrequency;
	private String kinkExcelname;
	private String kinkDeptKeyid;
	private String kinkCostarea;
	private String kinkTargetneed;
	private String kinkTempfield2;
	private String kinkTempfield3;
	private String kinkTempfield4;
	private String kinkTempfield5;
	private String kinkTempfield6;
	private String kinkTempfield7;
	private String kinkTempfield8;
	private String kinkTempfield9;
	private String kinkTempfield10;
	private String kinkActive;	
	private String kinkCreatedby;
	private String kinkCreatedon;
	private String kinkModifiedon;
	
	public KpiTlIndicatorKkBean()
	{			
	}
	
	public KpiTlIndicatorKkBean(FormModes mode)
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
	
	public String getKinkKeyid() {
		return kinkKeyid;
	}
	
	public void setTrarKeyid(String kinkKeyid) {
		this.kinkKeyid = kinkKeyid;
	}
	
	public String getKinkIndicatorname() {
		return kinkIndicatorname;
	}

	public void setKinkIndicatorname(String kinkIndicatorname) {
		this.kinkIndicatorname = kinkIndicatorname;
	}
	
	public String getKinkIndicatorcode() {
		return kinkIndicatorcode;
	}

	public void setTrarLevelNo(String kinkIndicatorcode) {
		this.kinkIndicatorcode = kinkIndicatorcode;
	}
	
	public String getKinkDescription() {
		return kinkDescription;
	}

	public void setKinkDescription(String kinkDescription) {
		this.kinkDescription = kinkDescription;
	}

	public String getKinkParentid() {
		return kinkParentid;
	}

	public void setKinkParentid(String kinkParentid) {
		this.kinkParentid = kinkParentid;
	}

	public String getKinkLevelno() {
		return kinkLevelno;
	}

	public void setKinkLevelno(String kinkLevelno) {
		this.kinkLevelno= kinkLevelno;
	}
	
	public String getKinkSortno() {
		return kinkSortno;
	}

	public void setKinkSortno(String kinkSortno) {
		this.kinkSortno= kinkSortno;
	}
	
	public String getKinkIschild() {
		return kinkIschild;
	}

	public void setKinkIschild(String kinkIschild) {
		this.kinkIschild = kinkIschild;
	}

	public String getKinkInputtype() {
		return kinkInputtype;
	}

	public void setKinkInputtype(String kinkInputtype) {
		this.kinkInputtype= kinkInputtype;
	}
	
	public String getKinkInputentry() {
		return kinkInputentry;
	}

	public void setKinkInputentry(String kinkInputentry) {
		this.kinkInputentry = kinkInputentry;
	}
	
	public String getKinkIdentifier() {
		return kinkIdentifier;
	}

	public void setKinkIdentifier(String kinkIdentifier) {
		this.kinkIdentifier = kinkIdentifier;
	}
	public String getKinkManualcalctype() {
		return kinkManualcalctype;
	}

	public void setKinkManualcalctype(String kinkManualcalctype) {
		this.kinkManualcalctype = kinkManualcalctype;
	}

	public String getKinkUomid() {
		return kinkUomid;
	}

	public void setKinkUomid(String kinkUomid) {
		this.kinkUomid = kinkUomid;
	}

	public String getKinkFrequency() {
		return kinkFrequency;
	}

	public void setKinkFrequency(String kinkFrequency) {
		this.kinkFrequency = kinkFrequency;
	}
	public String getKinkExcelname() {
		return kinkExcelname;
	}

	public void setKinkExcelname(String kinkExcelname) {
		this.kinkExcelname = kinkExcelname;
	}

	public String getKinkDeptKeyid() {
		return kinkDeptKeyid;
	}

	public void setKinkDeptKeyid(String kinkDeptKeyid) {
		this.kinkDeptKeyid = kinkDeptKeyid;
	}

	public String getKinkCostarea() {
		return kinkCostarea;
	}

	public void setKinkCostarea(String kinkCostarea) {
		this.kinkCostarea = kinkCostarea;
	}

	public String getKinkTempfield1() {
		return kinkTargetneed;
	}

	public void setKinkTargetneed(String kinkTargetneed) {
		this.kinkTargetneed = kinkTargetneed;
	}

	public String getKinkTempfield2() {
		return kinkTempfield2;
	}

	public void setKinkTempfield2(String kinkTempfield2) {
		this.kinkTempfield2 = kinkTempfield2;
	}

	public String getKinkTempfield3() {
		return kinkTempfield3;
	}

	public void setKinkTempfield3(String kinkTempfield3) {
		this.kinkTempfield3 = kinkTempfield3;
	}

	public String getKinkTempfield4() {
		return kinkTempfield4;
	}

	public void setKinkTempfield4(String kinkTempfield4) {
		this.kinkTempfield4 = kinkTempfield4;
	}

	public String getKinkTempfield5() {
		return kinkTempfield5;
	}

	public void setKinkTempfield5(String kinkTempfield5) {
		this.kinkTempfield5 = kinkTempfield5;
	}
	
	public String getKinkTempfield6() {
		return kinkTempfield6;
	}

	public void setKinkTempfield6(String kinkTempfield6) {
		this.kinkTempfield6 = kinkTempfield6;
	}

	public String getKinkTempfield7() {
		return kinkTempfield7;
	}

	public void setKinkTempfield7(String kinkTempfield7) {
		this.kinkTempfield7 = kinkTempfield7;
	}

	public String getKinkTempfield8() {
		return kinkTempfield8;
	}

	public void setKinkTempfield8(String kinkTempfield8) {
		this.kinkTempfield8 = kinkTempfield8;
	}

	public String getKinkTempfield9() {
		return kinkTempfield9;
	}

	public void setKinkTempfield9(String kinkTempfield9) {
		this.kinkTempfield9 = kinkTempfield9;
	}

	public String getKinkTempfield10() {
		return kinkTempfield10;
	}

	public void setKinkTempfield10(String kinkTempfield10) {
		this.kinkTempfield10 = kinkTempfield10;
	}
	
	public String getKinkActive() {
		return kinkActive;
	}

	public void setKinkActive(String kinkActive) {
		this.kinkActive = kinkActive;
	}

	public String getKinkCreatedby() {
		return kinkCreatedby;
	}

	public void setKinkCreatedby(String kinkCreatedby) {
		this.kinkCreatedby= kinkCreatedby;
	}

	public String getKinkCreatedon() {
		return kinkCreatedon;
	}

	public void setKinkCreatedon(String kinkCreatedon) {
		this.kinkCreatedon = kinkCreatedon;
	}

	public String getTrarModifiedon() {
		return kinkModifiedon;
	}

	public void setTrarModifiedon(String kinkModifiedon) {
		this.kinkModifiedon= kinkModifiedon;
	}
}
