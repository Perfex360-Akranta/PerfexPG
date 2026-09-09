package com.akranta.tpm.bean;

import com.akranta.tpm.model.Company;

public class CompanyBean {
	
	private Company company;
	private String mode;

	public void setCompanyMaster(Company company) {
		this.company = company;
	}
	public Company getCompanyMaster() {
		return company;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode() {
		return mode;
	}

}
