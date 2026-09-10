package com.akranta.tpm.bean;

import com.akranta.tpm.model.BAL_Company;

public class BAL_CompanyBean {
	
	private BAL_Company company;
	private String mode;

	public void setCompanyMaster(BAL_Company company) {
		this.company = company;
	}
	public BAL_Company getCompanyMaster() {
		return company;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode() {
		return mode;
	}

}
