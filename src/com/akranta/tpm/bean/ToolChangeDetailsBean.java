package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class ToolChangeDetailsBean {

	private String formActionMode;
	private String formMode;
	private String formHeader;	
	private FormModes formModes;
	private String disableForm ;
	private String keyId;
	private String toolKeyid;
	private String toolSrNo;
	private String changeddate;
	private String changetype;
	private String sectionid;
	private String cellid;
	private String machineid;
	private String flid;
	private String stdcotime;
	private String factoryid;
	private String remark;	
	private String actcotime;	
	private String estsharp;
	private String sharpno;
	private String lfwrtsharp;
	private String lastchangedate;
	private String prdlastchangedate;
	private String nextchangedate;
	
	public ToolChangeDetailsBean(FormModes mode) {
		
		if( FormModes.view == mode ){
			this.setDisableForm("true");
			this.formMode = FormModeConsts.view;
		}
		else{
			this.setDisableForm("false");
			this.formMode = FormModeConsts.create;
		}	
			
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
	public String getPrdlastchangedate() {
		return prdlastchangedate;
	}
	public void setPrdlastchangedate(String prdlastchangedate) {
		this.prdlastchangedate = prdlastchangedate;
	}
	public void setFormHeader(String formHeader) {
		this.formHeader = formHeader;
	}
	public FormModes getFormModes() {
		return formModes;
	}
	public void setFormModes(FormModes formModes) {
		this.formModes = formModes;
	}
	public String isDisableForm() {
		return disableForm;
	}
	public void setDisableForm(String disableForm) {
		this.disableForm = disableForm;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getToolKeyid() {
		return toolKeyid;
	}
	public void setToolKeyid(String toolKeyid) {
		this.toolKeyid = toolKeyid;
	}
	public String getToolSrNo() {
		return toolSrNo;
	}
	public void setToolSrNo(String toolSrNo) {
		this.toolSrNo = toolSrNo;
	}
	public String getChangeddate() {
		return changeddate;
	}
	public void setChangeddate(String changeddate) {
		this.changeddate = changeddate;
	}
	public String getChangetype() {
		return changetype;
	}
	public void setChangetype(String changetype) {
		this.changetype = changetype;
	}
	public String getSectionid() {
		return sectionid;
	}
	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	public String getCellid() {
		return cellid;
	}
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public String getMachineid() {
		return machineid;
	}
	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}
	public String getStdcotime() {
		return stdcotime;
	}
	public void setStdcotime(String stdcotime) {
		this.stdcotime = stdcotime;
	}
	public String getActcotime() {
		return actcotime;
	}
	public void setActcotime(String actcotime) {
		this.actcotime = actcotime;
	}
	public String getEstsharp() {
		return estsharp;
	}
	public void setEstsharp(String estsharp) {
		this.estsharp = estsharp;
	}
	public String getSharpno() {
		return sharpno;
	}
	public void setSharpno(String sharpno) {
		this.sharpno = sharpno;
	}
	public String getLfwrtsharp() {
		return lfwrtsharp;
	}
	public void setLfwrtsharp(String lfwrtsharp) {
		this.lfwrtsharp = lfwrtsharp;
	}
	public String getLastchangedate() {
		return lastchangedate;
	}
	public void setLastchangedate(String lastchangedate) {
		this.lastchangedate = lastchangedate;
	}
	public String getNextchangedate() {
		return nextchangedate;
	}
	public void setNextchangedate(String nextchangedate) {
		this.nextchangedate = nextchangedate;
	}
	public ToolChangeDetailsBean()
	{
		this.formMode = "CREATE";
	}
	public String getFactoryid() {
		return factoryid;
	}
	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getFlid() {
		return flid;
	}


	public void setFlid(String flid) {
		this.flid = flid;
	}

}
