package com.akranta.tpm.model;
/*business object */
import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private String empm_keyid            ;
	private String empm_name             ;
	private String empm_code             ;
	private String empm_employeetype     ;
	private String empm_employeenumber   ;
	private String empm_joineddate       ;
	private String empm_departmentid     ;
	private String empm_designationid    ;
	private String empm_factoryid        ;
	private String empm_isshiftincharge  ;
	private String empm_sectionid        ;
	private String empm_iscellmanager    ;
	private String empm_cellid           ;
	private String empm_tradeid          ;
	private String empm_extensionphone   ;
	private String empm_fax              ;
	private String empm_mobile           ;
	private String empm_email            ;
	private String empm_personalinfo     ;
	private String empm_remarks          ;
	private String empm_issectionmanager ;
	private String empm_skillcategory    ;
	private String empm_gradeid          ;
	private String empm_active           ;
	private String empm_createdby        ;
	private String empm_createdon        ;
	private String empm_modifiedon       ;

	private  Object [] saveArray = null;  
	
	private List<EmployeeDetail> employeeDetail ;
	
	public enum   tableFldConstants
	{
		keyid,name,code,employeetype,
		employeenumber,joineddate,departmentid,
		designationid,factoryid,isshiftincharge,
		sectionid,iscellmanager,cellid,tradeid,
		extensionphone,fax,mobile,email,
		personalinfo,remarks,issectionmanager,
		skillcategory,gradeid,active,
		createdby,createdon,modifiedon
	}
	
	
	public Employee()
	{
		setEmployeeDetail(new ArrayList<EmployeeDetail> ());

		saveArray = new  Object [ 27 ];
	}
	
	/**
	 * @param employeeDetail the employeeDetail to set
	 */
	public void setEmployeeDetail(List<EmployeeDetail> employeeDetail) {
		this.employeeDetail = employeeDetail;
	}

	/**
	 * @return the employeeDetail
	 */
	public List<EmployeeDetail> getEmployeeDetail() {
		
		return employeeDetail;
	}

	public String getEmpm_keyid() {
		 
		return empm_keyid;
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setEmpm_keyid(String empm_keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = empm_keyid;
		this.empm_keyid = empm_keyid;
	}
	public String getEmpm_name() {
		
		return empm_name;
	}
	public void setEmpm_name(String empm_name) {
		saveArray[  tableFldConstants.name.ordinal() ] = empm_name;
		this.empm_name = empm_name;
	}
	public String getEmpm_code() {
		return empm_code;
	}
	public void setEmpm_code(String empm_code) {
		saveArray[ tableFldConstants.code.ordinal() ] = empm_code;
		this.empm_code = empm_code;
	}
	public String getEmpm_employeetype() {
		return empm_employeetype;
	}
	public void setEmpm_employeetype(String empm_employeetype) {
		saveArray[ tableFldConstants.employeetype.ordinal() ] = empm_employeetype;
		this.empm_employeetype = empm_employeetype;
	}
	public String getEmpm_employeenumber() {
		return empm_employeenumber;
	}
	public void setEmpm_employeenumber(String empm_employeenumber) {
		saveArray[ tableFldConstants.employeenumber.ordinal() ] = empm_employeenumber;
		this.empm_employeenumber = empm_employeenumber;
	}
	public String getEmpm_joineddate() {
		return empm_joineddate;
	}
	public void setEmpm_joineddate(String empm_joineddate) {
		saveArray[ tableFldConstants.joineddate.ordinal() ] = empm_joineddate;
		this.empm_joineddate = empm_joineddate;
	}
	public String getEmpm_departmentid() {
		return empm_departmentid;
	}
	public void setEmpm_departmentid(String empm_departmentid) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = empm_joineddate;
		this.empm_departmentid = empm_departmentid;
	}
	public String getEmpm_designationid() {
		return empm_designationid;
	}
	public void setEmpm_designationid(String empm_designationid) {
		saveArray[ tableFldConstants.designationid.ordinal() ] = empm_designationid;
		this.empm_designationid = empm_designationid;
	}
	public String getEmpm_factoryid() {
		return empm_factoryid;
	}
	public void setEmpm_factoryid(String empm_factoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = empm_factoryid;
		this.empm_factoryid = empm_factoryid;
	}
	public String getEmpm_isshiftincharge() {
		return empm_isshiftincharge;
	}
	public void setEmpm_isshiftincharge(String empm_isshiftincharge) {
		saveArray[ tableFldConstants.isshiftincharge.ordinal() ] = empm_isshiftincharge;
		this.empm_isshiftincharge = empm_isshiftincharge;
	}
	public String getEmpm_sectionid() {
		return empm_sectionid;
	}
	public void setEmpm_sectionid(String empm_sectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = empm_sectionid;
		this.empm_sectionid = empm_sectionid;
	}
	public String getEmpm_iscellmanager() {
		return empm_iscellmanager;
	}
	public void setEmpm_iscellmanager(String empm_iscellmanager) {
		saveArray[ tableFldConstants.iscellmanager.ordinal() ] = empm_iscellmanager;
		this.empm_iscellmanager = empm_iscellmanager;
	}
	public String getEmpm_cellid() {
		return empm_cellid;
	}
	public void setEmpm_cellid(String empm_cellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = empm_cellid;
		this.empm_cellid = empm_cellid;
	}
	public String getEmpm_tradeid() {
		return empm_tradeid;
	}
	public void setEmpm_tradeid(String empm_tradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = empm_tradeid;
		this.empm_tradeid = empm_tradeid;
	}
	public String getEmpm_extensionphone() {
		return empm_extensionphone;
	}
	public void setEmpm_extensionphone(String empm_extensionphone) {
		saveArray[ tableFldConstants.extensionphone.ordinal() ] = empm_extensionphone;
		this.empm_extensionphone = empm_extensionphone;
	}
	public String getEmpm_fax() {
		return empm_fax;
	}
	public void setEmpm_fax(String empm_fax) {
		saveArray[ tableFldConstants.fax.ordinal() ] = empm_fax;
		this.empm_fax = empm_fax;
	}
	public String getEmpm_mobile() {
		return empm_mobile;
	}
	public void setEmpm_mobile(String empm_mobile) {
		saveArray[ tableFldConstants.mobile.ordinal() ] = empm_mobile;
		this.empm_mobile = empm_mobile;
	}
	public String getEmpm_email() {
		return empm_email;
	}
	public void setEmpm_email(String empm_email) {
		saveArray[ tableFldConstants.email.ordinal() ] = empm_email;
		this.empm_email = empm_email;
	}
	public String getEmpm_personalinfo() {
		return empm_personalinfo;
	}
	public void setEmpm_personalinfo(String empm_personalinfo) {
		saveArray[ tableFldConstants.personalinfo.ordinal() ] = empm_personalinfo;
		this.empm_personalinfo = empm_personalinfo;
	}
	public String getEmpm_remarks() {
		return empm_remarks;
	}
	public void setEmpm_remarks(String empm_remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = empm_remarks;
		this.empm_remarks = empm_remarks;
	}
	public String getEmpm_issectionmanager() {
		return empm_issectionmanager;
	}
	public void setEmpm_issectionmanager(String empm_issectionmanager) {
		saveArray[ tableFldConstants.isshiftincharge.ordinal() ] = empm_issectionmanager;
		this.empm_issectionmanager = empm_issectionmanager;
	}
	public String getEmpm_skillcategory() {
		return empm_skillcategory;
	}
	public void setEmpm_skillcategory(String empm_skillcategory) {
		saveArray[ tableFldConstants.skillcategory.ordinal() ] = empm_skillcategory;
		this.empm_skillcategory = empm_skillcategory;
	}
	public String getEmpm_gradeid() {
		return empm_gradeid;
	}
	public void setEmpm_gradeid(String empm_gradeid) {
		saveArray[ tableFldConstants.gradeid.ordinal() ] = empm_gradeid;
		this.empm_gradeid = empm_gradeid;
	}
	public String getEmpm_active() {
		return empm_active;
	}
	public void setEmpm_active(String empm_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = empm_active;
		this.empm_active = empm_active;
	}
	public String getEmpm_createdby() {
		return empm_createdby;
	}
	public void setEmpm_createdby(String empm_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = empm_createdby;
		this.empm_createdby = empm_createdby;
	}
	public String getEmpm_createdon() {
		return empm_createdon;
	}
	public void setEmpm_createdon(String empm_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = empm_createdon;
		this.empm_createdon = empm_createdon;
	}
	public String getEmpm_modifiedon() {
		return empm_modifiedon;
	}
	public void setEmpm_modifiedon(String empm_modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = empm_modifiedon;
		this.empm_modifiedon = empm_modifiedon;
	}


}
