package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class SheTlIncidentEmployeetBean {
	
	private  String formActionMode;
	private FormModes formMode;
	private String formHeader;
	private String contractEmp;
	private String contractComp;
	private String contract;
	
	
	
	public String getFormActionMode() {
		return formActionMode;
	}
	public void setFormActionMode(String formActionMode) {
		this.formActionMode = formActionMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public String getFormHeader() {
		return formHeader;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public String getContractEmp() {
		return contractEmp;
	}
	public void setContractEmp(String contractEmp) {
		this.contractEmp = contractEmp;
	}
	public String getContractComp() {
		return contractComp;
	}
	public void setContractComp(String contractComp) {
		this.contractComp = contractComp;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	

}
