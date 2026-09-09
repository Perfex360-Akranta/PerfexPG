package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTopicLinkRoledtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, topi_keyid, role_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlTopicLinkRoledtl()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getToprKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setToprKeyid(String toprKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = toprKeyid;
	}

	public String getToprTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setToprTopiKeyid(String toprTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = toprTopiKeyid;
	}

	public String getToprRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setToprRoleKeyid(String toprRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = toprRoleKeyid;
	}

	public String getToprTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setToprTempfield1(String toprTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = toprTempfield1;
	}

	public String getToprTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setToprTempfield2(String toprTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = toprTempfield2;
	}

	public String getToprTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setToprTempfield3(String toprTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = toprTempfield3;
	}

	public String getToprTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setToprTempfield4(String toprTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = toprTempfield4;
	}

	public String getToprTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setToprTempfield5(String toprTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = toprTempfield5;
	}

	public String getToprActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setToprActive(String toprActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = toprActive;
	}

	public String getToprCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setToprCreatedby(String toprCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = toprCreatedby;
	}

	public String getToprCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setToprCreatedon(String toprCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = toprCreatedon;
	}

	public String getToprModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setToprModifiedon(String toprModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = toprModifiedon;
	}

}

