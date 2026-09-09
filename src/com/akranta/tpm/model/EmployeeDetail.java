package com.akranta.tpm.model;
/*business object */
public class EmployeeDetail {
	
	private String empd_keyid                 ;
	private String empd_birthdate             ;
	private String empd_address               ;
	private String empd_cityid                ;
	private String empd_stateid               ;
	private String empd_countryid             ;
	private String empd_phone                 ;
	private String empd_image                 ;
	private String empd_remarks               ;
	private String empd_currentexperience     ;
	private String empd_otherexperience       ;
	private String empd_totalexperience       ;
	private String empd_qualification         ;
	private String empd_discipline            ;
	private String empd_active                ;
	private String empd_createdby             ;
	private String empd_createdon             ;
	private String empd_modifiedon            ;

	private Object [] saveArray = null;
	
	public EmployeeDetail()
	{
		setSaveArray(new Object[ 18 ]);
	}
	
	public String getEmpd_keyid() {
		return empd_keyid;
	}
	public void setEmpd_keyid(String empd_keyid) {
		this.empd_keyid = empd_keyid;
	}
	public String getEmpd_birthdate() {
		return empd_birthdate;
	}
	public void setEmpd_birthdate(String empd_birthdate) {
		this.empd_birthdate = empd_birthdate;
	}
	public String getEmpd_address() {
		return empd_address;
	}
	public void setEmpd_address(String empd_address) {
		this.empd_address = empd_address;
	}
	public String getEmpd_cityid() {
		return empd_cityid;
	}
	public void setEmpd_cityid(String empd_cityid) {
		this.empd_cityid = empd_cityid;
	}
	public String getEmpd_stateid() {
		return empd_stateid;
	}
	public void setEmpd_stateid(String empd_stateid) {
		this.empd_stateid = empd_stateid;
	}
	public String getEmpd_countryid() {
		return empd_countryid;
	}
	public void setEmpd_countryid(String empd_countryid) {
		this.empd_countryid = empd_countryid;
	}
	public String getEmpd_phone() {
		return empd_phone;
	}
	public void setEmpd_phone(String empd_phone) {
		this.empd_phone = empd_phone;
	}
	public String getEmpd_image() {
		return empd_image;
	}
	public void setEmpd_image(String empd_image) {
		this.empd_image = empd_image;
	}
	public String getEmpd_remarks() {
		return empd_remarks;
	}
	public void setEmpd_remarks(String empd_remarks) {
		this.empd_remarks = empd_remarks;
	}
	public String getEmpd_currentexperience() {
		return empd_currentexperience;
	}
	public void setEmpd_currentexperience(String empd_currentexperience) {
		this.empd_currentexperience = empd_currentexperience;
	}
	public String getEmpd_otherexperience() {
		return empd_otherexperience;
	}
	public void setEmpd_otherexperience(String empd_otherexperience) {
		this.empd_otherexperience = empd_otherexperience;
	}
	public String getEmpd_totalexperience() {
		return empd_totalexperience;
	}
	public void setEmpd_totalexperience(String empd_totalexperience) {
		this.empd_totalexperience = empd_totalexperience;
	}
	public String getEmpd_qualification() {
		return empd_qualification;
	}
	public void setEmpd_qualification(String empd_qualification) {
		this.empd_qualification = empd_qualification;
	}
	public String getEmpd_discipline() {
		return empd_discipline;
	}
	public void setEmpd_discipline(String empd_discipline) {
		this.empd_discipline = empd_discipline;
	}
	public String getEmpd_active() {
		return empd_active;
	}
	public void setEmpd_active(String empd_active) {
		this.empd_active = empd_active;
	}
	public String getEmpd_createdby() {
		return empd_createdby;
	}
	public void setEmpd_createdby(String empd_createdby) {
		this.empd_createdby = empd_createdby;
	}
	public String getEmpd_createdon() {
		return empd_createdon;
	}
	public void setEmpd_createdon(String empd_createdon) {
		this.empd_createdon = empd_createdon;
	}
	public String getEmpd_modifiedon() {
		return empd_modifiedon;
	}
	public void setEmpd_modifiedon(String empd_modifiedon) {
		this.empd_modifiedon = empd_modifiedon;
	}

	/**
	 * @param saveArray the saveArray to set
	 */
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	/**
	 * @return the saveArray
	 */
	public Object [] getSaveArray() {
		return saveArray;
	}


}
