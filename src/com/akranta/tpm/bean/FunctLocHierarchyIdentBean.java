package com.akranta.tpm.bean;

import com.akranta.tpm.utils.FormModes;

public class FunctLocHierarchyIdentBean {
	
	private HtmlElementBean company;
	private HtmlElementBean location;
	//fact// private HtmlElementBean factory;
	private HtmlElementBean sbu;
	private HtmlElementBean pbu;
	private HtmlElementBean section;
	private HtmlElementBean cell;
	private HtmlElementBean team;
	private HtmlElementBean machine;
	private HtmlElementBean flid;
	
	private FormModes formMode;
	
	public FunctLocHierarchyIdentBean(){
		company = new HtmlElementBean();
		location = new HtmlElementBean();
		//fact// factory = new HtmlElementBean();
		section = new HtmlElementBean();
		cell = new HtmlElementBean();
		team = new HtmlElementBean();
		machine = new HtmlElementBean();
		sbu = new HtmlElementBean();
		pbu = new HtmlElementBean();
		company.setId("company");
		location.setId("location");
		//fact// factory.setId("factory");
		section.setId("section");
		cell.setId("cell");
		team.setId("team");
		machine.setId("machine");
		sbu.setId("sbu");
		pbu.setId("pbu");
	}
	public HtmlElementBean getCompany() {
		return company;
	}
	public void setCompany(HtmlElementBean company) {
		this.company = company;
		company.setId("company");
	}
	public HtmlElementBean getLocation() {
		return location;
	}
	public void setLocation(HtmlElementBean location) {
		this.location = location;
		location.setId("location");
	}
	//fact// 
	/*
	public HtmlElementBean getFactory() {
		return factory;
	}
	public void setFactory(HtmlElementBean factory) {
		this.factory = factory;
		factory.setId("factory");
	}
	*/
	public HtmlElementBean getSection() {
		return section;
	}
	public void setSection(HtmlElementBean section) {
		this.section = section;
		section.setId("section");
	}
	public HtmlElementBean getCell() {
		return cell;
	}
	public void setCell(HtmlElementBean cell) {
		this.cell = cell;
		cell.setId("cell");
	}
	public HtmlElementBean getTeam() {
		return team;
	}
	public void setTeam(HtmlElementBean team) {
		this.team = team;
		team.setId("team");
	}
	public HtmlElementBean getMachine() {
		return machine;
	}
	public void setMachine(HtmlElementBean machine) {
		this.machine = machine;
		machine.setId("machine");
	}
	public void setFormMode(FormModes formMode) {
		this.formMode = formMode;
	}
	public FormModes getFormMode() {
		return formMode;
	}
	public void setFlid(HtmlElementBean flid) {
		this.flid = flid;
	}
	public HtmlElementBean getFlid() {
		return flid;
	}
	public void setSbu(HtmlElementBean sbu) {
		this.sbu = sbu;
		sbu.setId("sbu");
	}
	public HtmlElementBean getSbu() {
		return sbu;
	}
	public void setPbu(HtmlElementBean pbu) {
		this.pbu = pbu;
		pbu.setId("pbu");
	}
	public HtmlElementBean getPbu() {
		return pbu;
	}
	

}
