package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class SheAccIncBean {
	
	private String sincReportedtime;
	private String sincOccurredtime;
	private String sincAnalysedtime;
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String saveMode;
	private FormModes formModes;
	private boolean disableForm ;
	private boolean disableOccurredDate;
	private boolean disableOccurredtime;
	private boolean disableOccurredShift;
	private boolean disableAccidentType;
	private boolean disableDepartment;
	private boolean disableReportedDate;	
	private boolean disableReportedtime;
	private boolean disableReportedShift;
	private boolean disableReportedBy;
	private boolean disableDivision;
	private boolean disableArea;
	private boolean disableAccidentDesc;
	private boolean disableImmediateAction;
	private boolean disableRecommendations;	
	private boolean disabledispFunctionalLoc;
	private boolean disableAnalysedDate;	
	private boolean disableAnalysedtime;	
	private boolean disableAnalysedShift;	
	private boolean disableAnalysedBy;	
	private boolean disableExactType;	
	private boolean disableYyno;	
	private boolean disableUnsafeAct;	
	private boolean disableUnsafeCondn;	
	private boolean disableExactDesc;
	private boolean disableIdea;	
	private boolean disableRootcause;	
	private boolean disablePhenomena;	
	private boolean disableCountermeasure;	
	private boolean disableLossproperty;	
	private boolean disableDirectcost;	
	private String before;
	private String after;
	
	public SheAccIncBean()
	{
		this.formMode = "CREATE";
	}
	public SheAccIncBean(FormModes mode)
	{
		formModeSettings(mode);
	}
	public SheAccIncBean(FormModes mode,String woMode)
	{
		formModeSettings(mode,woMode);
	}
	private void formModeSettings(FormModes mode){
		this.formModes = mode;
		if( mode == FormModes.modify){			
			this.disableForm = true;
			this.formActionMode = FormModeConsts.view;
			this.disableOccurredDate = this.disableOccurredtime =  this.disableReportedDate = this.disableReportedtime = true;
			this.disableOccurredShift = this.disableReportedShift = this.disableAccidentType = this.disableReportedBy = true;
			this.disableDepartment = this.disableArea = this.disableDivision = this.disableImmediateAction = true;
			this.disableAccidentDesc = this.disableRecommendations = this.disableLossproperty = this.disableDirectcost=true;
		}
		else if( mode == FormModes.view ){
				//this.setDisableForm(false);
				this.disableForm = true;
				this.formActionMode = FormModeConsts.view;
				this.disableOccurredDate = this.disableOccurredtime =  this.disableReportedDate = this.disableReportedtime = true;
				this.disableOccurredShift = this.disableReportedShift = this.disableAccidentType = this.disableReportedBy = true;
				this.disableDepartment = this.disableArea = this.disableDivision = this.disableImmediateAction = true;
				this.disableAccidentDesc = this.disableRecommendations =  this.disableLossproperty = this.disableDirectcost= true;
				this.disableAnalysedDate = this.disableAnalysedtime =  this.disableAnalysedShift = this.disableAnalysedBy = true;
				this.disableExactType = this.disableYyno = this.disableUnsafeAct = this.disableUnsafeCondn = true;
				this.disableExactDesc = this.disableIdea = this.disableRootcause = this.disablePhenomena = this.disableCountermeasure = true;
			}
		else if( mode == FormModes.create ){
			this.disableAnalysedDate = this.disableAnalysedtime =  this.disableAnalysedShift = this.disableAnalysedBy = true;
			this.disableExactType = this.disableYyno = this.disableUnsafeAct = this.disableUnsafeCondn = true;
			this.disableExactDesc = this.disableIdea = this.disableRootcause = this.disablePhenomena = this.disableCountermeasure = true;
		}
			this.formActionMode = FormModeConsts.create;
	}
	
	private void formModeSettings(FormModes mode,String woMode){
		this.formModes = mode;
		if( mode == FormModes.view ){
			this.setDisableForm(false);
			//this.disableForm = true;
			//this.formActionMode = FormModeConsts.view;
		}
		else
			this.formActionMode = FormModeConsts.create;
	}
	
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getSaveMode() {
		return saveMode;
	}
	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}
	public FormModes getFormModes() {
		return formModes;
	}
	public void setFormModes(FormModes formModes) {
		this.formModes = formModes;
	}
	public boolean isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(boolean disableForm) {
		this.disableForm = disableForm;
	}
	public boolean isDisableOccurredDate() {
		return disableOccurredDate;
	}
	public void setDisableOccurredDate(boolean disableOccurredDate) {
		this.disableOccurredDate = disableOccurredDate;
	}
	public boolean isDisableOccurredtime() {
		return disableOccurredtime;
	}
	public void setDisableOccurredtime(boolean disableOccurredtime) {
		this.disableOccurredtime = disableOccurredtime;
	}
	public boolean isDisableOccurredShift() {
		return disableOccurredShift;
	}
	public void setDisableOccurredShift(boolean disableOccurredShift) {
		this.disableOccurredShift = disableOccurredShift;
	}
	public boolean isDisableAccidentType() {
		return disableAccidentType;
	}
	public void setDisableAccidentType(boolean disableAccidentType) {
		this.disableAccidentType = disableAccidentType;
	}
	public boolean isDisableDepartment() {
		return disableDepartment;
	}
	public void setDisableDepartment(boolean disableDepartment) {
		this.disableDepartment = disableDepartment;
	}
	public boolean isDisableReportedDate() {
		return disableReportedDate;
	}
	public void setDisableReportedDate(boolean disableReportedDate) {
		this.disableReportedDate = disableReportedDate;
	}
	public boolean isDisableReportedtime() {
		return disableReportedtime;
	}
	public void setDisableReportedtime(boolean disableReportedtime) {
		this.disableReportedtime = disableReportedtime;
	}
	public boolean isDisableReportedShift() {
		return disableReportedShift;
	}
	public void setDisableReportedShift(boolean disableReportedShift) {
		this.disableReportedShift = disableReportedShift;
	}
	public boolean isDisableReportedBy() {
		return disableReportedBy;
	}
	public void setDisableReportedBy(boolean disableReportedBy) {
		this.disableReportedBy = disableReportedBy;
	}
	public boolean isDisableDivision() {
		return disableDivision;
	}
	public void setDisableDivision(boolean disableDivision) {
		this.disableDivision = disableDivision;
	}
	public boolean isDisableArea() {
		return disableArea;
	}
	public void setDisableArea(boolean disableArea) {
		this.disableArea = disableArea;
	}
	public boolean isDisableAccidentDesc() {
		return disableAccidentDesc;
	}
	public void setDisableAccidentDesc(boolean disableAccidentDesc) {
		this.disableAccidentDesc = disableAccidentDesc;
	}
	public boolean isDisableImmediateAction() {
		return disableImmediateAction;
	}
	public void setDisableImmediateAction(boolean disableImmediateAction) {
		this.disableImmediateAction = disableImmediateAction;
	}
	public boolean isDisableRecommendations() {
		return disableRecommendations;
	}
	public void setDisableRecommendations(boolean disableRecommendations) {
		this.disableRecommendations = disableRecommendations;
	}
	public boolean isDisableDispFunctionalLoc() {
		return disabledispFunctionalLoc;
	}
	public void setDisableDispFunctionalLoc(boolean disabledispFunctionalLoc) {
		this.disabledispFunctionalLoc = disabledispFunctionalLoc;
	}
	
	public String getSincReportedtime() {
		return sincReportedtime;
	}
	public void setSincReportedtime(String sincReportedtime) {
		this.sincReportedtime = sincReportedtime;
	}
	public String getSincOccurredtime() {
		return sincOccurredtime;
	}
	public void setSincOccurredtime(String sincOccurredtime) {
		this.sincOccurredtime = sincOccurredtime;
	}
	public String getSincAnalysedtime() {
		return sincAnalysedtime;
	}
	public void setSincAnalysedtime(String sincAnalysedtime) {
		this.sincAnalysedtime = sincAnalysedtime;
	}
	public boolean isDisabledispFunctionalLoc() {
		return disabledispFunctionalLoc;
	}
	public void setDisabledispFunctionalLoc(boolean disabledispFunctionalLoc) {
		this.disabledispFunctionalLoc = disabledispFunctionalLoc;
	}
	public boolean isDisableAnalysedDate() {
		return disableAnalysedDate;
	}
	public void setDisableAnalysedDate(boolean disableAnalysedDate) {
		this.disableAnalysedDate = disableAnalysedDate;
	}
	public boolean isDisableAnalysedtime() {
		return disableAnalysedtime;
	}
	public void setDisableAnalysedtime(boolean disableAnalysedtime) {
		this.disableAnalysedtime = disableAnalysedtime;
	}
	public boolean isDisableAnalysedShift() {
		return disableAnalysedShift;
	}
	public void setDisableAnalysedShift(boolean disableAnalysedShift) {
		this.disableAnalysedShift = disableAnalysedShift;
	}
	public boolean isDisableAnalysedBy() {
		return disableAnalysedBy;
	}
	public void setDisableAnalysedBy(boolean disableAnalysedBy) {
		this.disableAnalysedBy = disableAnalysedBy;
	}
	public boolean isDisableExactType() {
		return disableExactType;
	}
	public void setDisableExactType(boolean disableExactType) {
		this.disableExactType = disableExactType;
	}
	public boolean isDisableYyno() {
		return disableYyno;
	}
	public void setDisableYyno(boolean disableYyno) {
		this.disableYyno = disableYyno;
	}
	public boolean isDisableUnsafeAct() {
		return disableUnsafeAct;
	}
	public void setDisableUnsafeAct(boolean disableUnsafeAct) {
		this.disableUnsafeAct = disableUnsafeAct;
	}
	public boolean isDisableUnsafeCondn() {
		return disableUnsafeCondn;
	}
	public void setDisableUnsafeCondn(boolean disableUnsafeCondn) {
		this.disableUnsafeCondn = disableUnsafeCondn;
	}
	public boolean isDisableExactDesc() {
		return disableExactDesc;
	}
	public void setDisableExactDesc(boolean disableExactDesc) {
		this.disableExactDesc = disableExactDesc;
	}
	public boolean isDisableIdea() {
		return disableIdea;
	}
	public void setDisableIdea(boolean disableIdea) {
		this.disableIdea = disableIdea;
	}
	public boolean isDisableRootcause() {
		return disableRootcause;
	}
	public void setDisableRootcause(boolean disableRootcause) {
		this.disableRootcause = disableRootcause;
	}
	public boolean isDisablePhenomena() {
		return disablePhenomena;
	}
	public void setDisablePhenomena(boolean disablePhenomena) {
		this.disablePhenomena = disablePhenomena;
	}
	public boolean isDisableCountermeasure() {
		return disableCountermeasure;
	}
	public void setDisableCountermeasure(boolean disableCountermeasure) {
		this.disableCountermeasure = disableCountermeasure;
	}
	public boolean isDisableLossproperty() {
		return disableLossproperty;
	}
	public void setDisableLossproperty(boolean disableLossproperty) {
		this.disableLossproperty = disableLossproperty;
	}
	public boolean isDisableDirectcost() {
		return disableDirectcost;
	}
	public void setDisableDirectcost(boolean disableDirectcost) {
		this.disableDirectcost = disableDirectcost;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getBefore() {
		return before;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public String getAfter() {
		return after;
	}

}
