package com.akranta.tpm.bean;

public class EmployeeBean {
	
	private String formActionMode;
	private String formMode;
	private String formHeader;
	private String empdBirthdate;
	private String empmJoineddate;
	private String empIdForFuncLoc;
	private String funcloctypeForFuncLoc;
	
	public EmployeeBean()
	{
		this.formMode = "CREATE";
		//this.disableBtnExcelView = true;
		//this.disableChkApprovedBy= true;
		//this.disableCmbApprovedBy= true;
		//this.disableDteApprovedDate = true; 
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setEmpdBirthdate(String empdBirthdate) {
		this.empdBirthdate = empdBirthdate;
	}
	public String getEmpdBirthdate() {
		return empdBirthdate;
	}
	public void setEmpmJoineddate(String empmJoineddate) {
		this.empmJoineddate = empmJoineddate;
	}
	public String getEmpmJoineddate() {
		return empmJoineddate;
	}
	public void setEmpIdForFuncLoc(String empIdForFuncLoc) {
		this.empIdForFuncLoc =  empIdForFuncLoc;
		
	}
	public String getEmpIdForFuncLoc() {
		return empIdForFuncLoc ;
		
	}
	public void setFuncloctypeForFuncLoc(String funcloctypeForFuncLoc) {
		this.funcloctypeForFuncLoc = funcloctypeForFuncLoc;
	}
	public String getFuncloctypeForFuncLoc() {
		return funcloctypeForFuncLoc;
	}
	
}
