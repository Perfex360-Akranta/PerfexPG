package com.akranta.tpm.model;

public class FactoryLayout {
	
	private FuntLocnElementDispModel company;
	private FuntLocnElementDispModel location;
	private FuntLocnElementDispModel factory;
	private FuntLocnElementDispModel sbu;
	private FuntLocnElementDispModel pbu;
	private FuntLocnElementDispModel section;
	private FuntLocnElementDispModel cell;
	private FuntLocnElementDispModel team;
	private FuntLocnElementDispModel machine;
	private FuntLocnElementDispModel flid;

	private static final String separator = "-";
	
	public FuntLocnElementDispModel getCompany() {
		return company;
	}
	public void setCompany(FuntLocnElementDispModel company) {
		this.company = company;
	}
	public FuntLocnElementDispModel getLocation() {
		return location;
	}
	public void setLocation(FuntLocnElementDispModel location) {
		this.location = location;
	}
	public FuntLocnElementDispModel getFactory() {
		return factory;
	}
	public void setFactory(FuntLocnElementDispModel factory) {
		this.factory = factory;
	}
	public FuntLocnElementDispModel getSection() {
		return section;
	}
	public void setSection(FuntLocnElementDispModel section) {
		this.section = section;
	}
	public FuntLocnElementDispModel getCell() {
		return cell;
	}
	public void setCell(FuntLocnElementDispModel cell) {
		this.cell = cell;
	}
	public FuntLocnElementDispModel getMachine() {
		return machine;
	}
	public void setMachine(FuntLocnElementDispModel machine) {
		this.machine = machine;
	}
	public void setTeam(FuntLocnElementDispModel team) {
		this.team = team;
	}
	public FuntLocnElementDispModel getTeam() {
		return team;
	}
	public void setFlid(FuntLocnElementDispModel flid) {
		this.flid = flid;
	}
	public FuntLocnElementDispModel getFlid() {
		return flid;
	}
	public void setSbu(FuntLocnElementDispModel sbu) {
		this.sbu = sbu;
	}
	public FuntLocnElementDispModel getSbu() {
		return sbu;
	}
	public void setPbu(FuntLocnElementDispModel pbu) {
		this.pbu = pbu;
	}
	public FuntLocnElementDispModel getPbu() {
		return pbu;
	}
	
	public String getElementId(){
		StringBuilder elementId = new StringBuilder();
		if( this.company != null && this.company.getKeyid() != null && ! this.company.getKeyid().isEmpty() )
			elementId.append(this.company.getKeyid());
		if( elementId.length() > 0 &&  this.location != null && this.location.getKeyid() != null && ! this.location.getKeyid().isEmpty() )
			elementId.append(separator + this.location.getKeyid());
		if( elementId.length() > 0 && this.factory != null && this.factory.getKeyid() != null && ! this.factory.getKeyid().isEmpty() )
			elementId.append(separator + this.factory.getKeyid());
		if( elementId.length() > 0 && this.sbu != null && this.sbu.getKeyid() != null && ! this.sbu.getKeyid().isEmpty() )
			elementId.append(separator + this.sbu.getKeyid());
		if( elementId.length() > 0 && this.pbu != null && this.pbu.getKeyid() != null && ! this.pbu.getKeyid().isEmpty() )
			elementId.append(separator + this.pbu.getKeyid());
		if( elementId.length() > 0 && this.section != null && this.section.getKeyid() != null && ! this.section.getKeyid().isEmpty() )
			elementId.append(separator + this.section.getKeyid());
		if( elementId.length() > 0 && this.cell != null && this.cell.getKeyid() != null && ! this.cell.getKeyid().isEmpty() )
			elementId.append(separator + this.cell.getKeyid());
		if( elementId.length() > 0 && this.machine != null && this.machine.getKeyid() != null && ! this.machine.getKeyid().isEmpty() )
			elementId.append(separator + this.machine.getKeyid());
		
		return elementId.toString();
	}
	

	public String getOriginalId(){
		//StringBuilder elementId = new StringBuilder();
		if( this.machine != null && this.machine.getKeyid() != null && ! this.machine.getKeyid().isEmpty() )
			 return this.machine.getKeyid();
		if( this.cell != null && this.cell.getKeyid() != null && ! this.cell.getKeyid().isEmpty() )
			 return this.cell.getKeyid();
		if( this.section != null && this.section.getKeyid() != null && ! this.section.getKeyid().isEmpty() )
			return  this.section.getKeyid();
		if( this.pbu != null && this.pbu.getKeyid() != null && ! this.pbu.getKeyid().isEmpty() )
			return this.pbu.getKeyid();
		if( this.sbu != null && this.sbu.getKeyid() != null && ! this.sbu.getKeyid().isEmpty() )
			return this.sbu.getKeyid();
		if( this.factory != null && this.factory.getKeyid() != null && ! this.factory.getKeyid().isEmpty() )
			return this.factory.getKeyid();
		
		if( this.location != null && this.location.getKeyid() != null && ! this.location.getKeyid().isEmpty() )
			return this.location.getKeyid();
		
		if( this.company != null && this.company.getKeyid() != null && ! this.company.getKeyid().isEmpty() )
			return this.company.getKeyid();
		return null;
	}


}

