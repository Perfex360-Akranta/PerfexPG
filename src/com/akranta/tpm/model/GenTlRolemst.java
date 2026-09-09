package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlRolemst {
	private List<GenTlEmployeemst> employeedetails ;
	private  Object [] saveArray = null;  
    	

	public enum   tableFldConstants
	{
		keyid, code, name, description, remarks, fact_keyid, flid
		, elementid, tempfield3, level, active, createdby, createdon
		, modifiedon
	}

	public GenTlRolemst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getRoleKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRoleKeyid(String roleKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = roleKeyid;
	}

	public String getRoleCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setRoleCode(String roleCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = roleCode;
	}

	public String getRoleName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setRoleName(String roleName) {
		saveArray[ tableFldConstants.name.ordinal() ] = roleName;
	}

	public String getRoleDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setRoleDescription(String roleDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = roleDescription;
	}

	public String getRoleRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setRoleRemarks(String roleRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = roleRemarks;
	}

	public String getRoleFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setRoleFactKeyid(String roleFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = roleFactKeyid;
	}

	public String getRoleFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setRoleFlid(String roleFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = roleFlid;
	}

	public String getRoleElementId() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setRoleElementId(String roleElementId) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = roleElementId;
	}

	public String getRoleTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRoleTempfield3(String roleTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = roleTempfield3;
	}

	public String getRoleLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setRoleLevel(String roleLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = roleLevel;
	}

	public String getRoleActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRoleActive(String roleActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = roleActive;
	}

	public String getRoleCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRoleCreatedby(String roleCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = roleCreatedby;
	}

	public String getRoleCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRoleCreatedon(String roleCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = roleCreatedon;
	}

	public String getRoleModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRoleModifiedon(String roleModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = roleModifiedon;
	}

	
	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
		this.saveArray = dataArr;
	}

	public void setEmployeedetails(List<GenTlEmployeemst> newGenTlEmployeemst) {
		this.employeedetails = newGenTlEmployeemst;
	}

	public List<GenTlEmployeemst> getEmployeedetails() {
		return employeedetails;
	}

	 
}

