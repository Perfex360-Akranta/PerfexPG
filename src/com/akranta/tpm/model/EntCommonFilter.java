package com.akranta.tpm.model;

public class EntCommonFilter {

	private ComboFilter designation;
	private ComboFilter progm;
	private ComboFilter pgmbenefit;	
	private ComboFilter batch;
	private ComboFilter pgmno;
	private ComboFilter trainingtype;
	private ComboFilter knowavg;
	private ComboFilter skillavg;
	private ComboFilter compavg;
	private ComboFilter trainingcategory;
	
	
	public void setDesignation(ComboFilter designation) {
		this.designation = designation;
	}
	public ComboFilter getDesignation() {
		return designation;
	}
	public void setProgm(ComboFilter progm) {
		this.progm = progm;
	}
	public ComboFilter getProgm() {
		return progm;
	}
	public void setPgmbenefit(ComboFilter pgmbenefit) {
		this.pgmbenefit = pgmbenefit;
	}
	public ComboFilter getPgmbenefit() {
		return pgmbenefit;
	}
	public void setBatch(ComboFilter batch) {
		this.batch = batch;
	}
	public ComboFilter getBatch() {
		return batch;
	}
	public void setPgmno(ComboFilter pgmno) {
		this.pgmno = pgmno;
	}
	public ComboFilter getPgmno() {
		return pgmno;
	}
	public void setTrainingtype(ComboFilter trainingtype) {
		this.trainingtype = trainingtype;
	}
	public ComboFilter getTrainingtype() {
		return trainingtype;
	}
	public void setSkillavg(ComboFilter skillavg) {
		this.skillavg = skillavg;
	}
	public ComboFilter getSkillavg() {
		return skillavg;
	}
	public void setCompavg(ComboFilter compavg) {
		this.compavg = compavg;
	}
	public ComboFilter getCompavg() {
		return compavg;
	}
	public void setTrainingcategory(ComboFilter trainingcategory) {
		this.trainingcategory = trainingcategory;
	}
	public ComboFilter getTrainingcategory() {
		return trainingcategory;
	}
	public void setKnowavg(ComboFilter knowavg) {
		this.knowavg = knowavg;
	}
	public ComboFilter getKnowavg() {
		return knowavg;
	}
	
}
