package com.akranta.tpm.bean;

public class ActionPlanParams extends CommonParams {
	private String employeeId;
	private String mode;
	public final String MODE_VIEW ="view";
	public final String MODE_COMPLETION ="completion";
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
