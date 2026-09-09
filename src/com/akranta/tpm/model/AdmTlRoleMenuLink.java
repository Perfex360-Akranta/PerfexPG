package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlRoleMenuLink {

	private  Object [] saveArray = null;  
	private List<AdmTlRoleMenuLink> admRoleMenuLink ;

	public enum   tableFldConstants
	{
		roleid, menuid, active, createdby, createdon, modifiedon
	}

	public AdmTlRoleMenuLink()
	{
		setAdmRoleMenuLink(new ArrayList<AdmTlRoleMenuLink> ());
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getArmlRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setArmlRoleid(String armlRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = armlRoleid;
	}

	public String getArmlMenuid() {
		return (String) saveArray[ tableFldConstants.menuid.ordinal() ];
	}

	public void setArmlMenuid(String armlMenuid) {
		saveArray[ tableFldConstants.menuid.ordinal() ] = armlMenuid;
	}

	public String getArmlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setArmlActive(String armlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = armlActive;
	}

	public String getArmlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setArmlCreatedby(String armlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = armlCreatedby;
	}

	public String getArmlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setArmlCreatedon(String armlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = armlCreatedon;
	}

	public String getArmlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setArmlModifiedon(String armlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = armlModifiedon;
	}

	public List<AdmTlRoleMenuLink> getAdmRoleMenuLink() {
		return admRoleMenuLink;
	}

	public void setAdmRoleMenuLink(List<AdmTlRoleMenuLink> admRoleMenuLink) {
		this.admRoleMenuLink = admRoleMenuLink;
	}

}

