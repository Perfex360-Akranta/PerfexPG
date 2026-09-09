package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class HorizontalDeploymentBean {

private String kaizenId;
private String factoryId;
private String sectionId;
private String cellId;
private String machineId;
private String assemblyId;
private String phenomenaId;
private String causeId;
private String startDate;
private String endDate;
private String theme;
private boolean disableField;
private boolean disableKhdmCellid;
private boolean disableKhdmMachineid;
private boolean disableTheme;
private boolean disablestartDate;
private boolean disableendDate;
private boolean disableBtnCell;
private boolean disableBtnMachine;
private String createdBy;
private String formMode;

public HorizontalDeploymentBean()
{

	
}
public void setCellId(String cellId) {
	this.cellId = cellId;
}
public String getCellId() {
	return cellId;
}
public void setMachineId(String machineId) {
	this.machineId = machineId;
}
public String getMachineId() {
	return machineId;
}
public void setAssemblyId(String assemblyId) {
	this.assemblyId = assemblyId;
}
public String getAssemblyId() {
	return assemblyId;
}
public void setPhenomenaId(String phenomenaId) {
	this.phenomenaId = phenomenaId;
}
public String getPhenomenaId() {
	return phenomenaId;
}
public void setCauseId(String causeId) {
	this.causeId = causeId;
}
public String getCauseId() {
	return causeId;
}

public void setDisableField(boolean disableField) {
	this.disableField = disableField;
}

public boolean isDisableField() {
	return disableField;
}

public void setSectionId(String sectionId) {
	this.sectionId = sectionId;
}

public String getSectionId() {
	return sectionId;
}

public void setKaizenId(String kaizenId) {
	this.kaizenId = kaizenId;
}

public String getKaizenId() {
	return kaizenId;
}

public void setFactoryId(String factoryId) {
	this.factoryId = factoryId;
}

public String getFactoryId() {
	return factoryId;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public String getCreatedBy() {
	return createdBy;
}

public void setDisableKhdmCellid(boolean disableKhdmCellid) {
	this.disableKhdmCellid = disableKhdmCellid;
}

public boolean isDisableKhdmCellid() {
	return disableKhdmCellid;
}

public void setDisableKhdmMachineid(boolean disableKhdmMachineid) {
	this.disableKhdmMachineid = disableKhdmMachineid;
}

public boolean isDisableKhdmMachineid() {
	return disableKhdmMachineid;
}

public void setStartDate(String startDate) {
	this.startDate = startDate;
}

public String getStartDate() {
	return startDate;
}

public void setEndDate(String endDate) {
	this.endDate = endDate;
}

public String getEndDate() {
	return endDate;
}

public void setTheme(String theme) {
	this.theme = theme;
}

public String getTheme() {
	return theme;
}

public void setDisablestartDate(boolean disablestartDate) {
	this.disablestartDate = disablestartDate;
}

public boolean isDisablestartDate() {
	return disablestartDate;
}

public void setDisableTheme(boolean disableTheme) {
	this.disableTheme = disableTheme;
}

public boolean isDisableTheme() {
	return disableTheme;
}

public void setDisableendDate(boolean disableendDate) {
	this.disableendDate = disableendDate;
}

public boolean isDisableendDate() {
	return disableendDate;
}

public void setDisableBtnMachine(boolean disableBtnMachine) {
	this.disableBtnMachine = disableBtnMachine;
}

public boolean isDisableBtnMachine() {
	return disableBtnMachine;
}

public void setDisableBtnCell(boolean disableBtnCell) {
	this.disableBtnCell = disableBtnCell;
}

public boolean isDisableBtnCell() {
	return disableBtnCell;
}

public void setFormMode(String formMode) {
	this.formMode = formMode;
}

public String getFormMode() {
	return formMode;
}

	
	
}
