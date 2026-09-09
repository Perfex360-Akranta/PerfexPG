package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleTrainingareaLink {

	private  Object [] saveArray = null;  
	private EntTlRoleTopicLink entTlRoleTopicLink =null;

	public enum   tableFldConstants
	{
		keyid, trar_keyid, role_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlRoleTrainingareaLink()
	{
		saveArray = new  Object [ 12 ];
		
		entTlRoleTopicLink = new EntTlRoleTopicLink();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] savearray) {
		this.saveArray = savearray;
	}
	
	public String getRtalKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRtalKeyid(String rtalKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rtalKeyid;
	}

	public String getRtalTrarKeyid() {
		return (String) saveArray[ tableFldConstants.trar_keyid.ordinal() ];
	}

	public void setRtalTrarKeyid(String rtalTrarKeyid) {
		saveArray[ tableFldConstants.trar_keyid.ordinal() ] = rtalTrarKeyid;
	}

	public String getRtalRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setRtalRoleKeyid(String rtalRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = rtalRoleKeyid;
	}

	public String getRtalTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRtalTempfield1(String rtalTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rtalTempfield1;
	}

	public String getRtalTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRtalTempfield2(String rtalTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rtalTempfield2;
	}

	public String getRtalTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRtalTempfield3(String rtalTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rtalTempfield3;
	}

	public String getRtalTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRtalTempfield4(String rtalTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rtalTempfield4;
	}

	public String getRtalTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setRtalTempfield5(String rtalTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rtalTempfield5;
	}

	public String getRtalActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRtalActive(String rtalActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rtalActive;
	}

	public String getRtalCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRtalCreatedby(String rtalCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rtalCreatedby;
	}

	public String getRtalCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRtalCreatedon(String rtalCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rtalCreatedon;
	}

	public String getRtalModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRtalModifiedon(String rtalModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rtalModifiedon;
	}

	public void setEntTlRoleTopicLink(EntTlRoleTopicLink entTlRoleTopicLink) {
		this.entTlRoleTopicLink = entTlRoleTopicLink;
	}

	public EntTlRoleTopicLink getEntTlRoleTopicLink() {
		return entTlRoleTopicLink;
	}

}

