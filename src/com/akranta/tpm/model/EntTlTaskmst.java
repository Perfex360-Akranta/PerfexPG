package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTaskmst {

	private  Object [] saveArray = null;  
	private String tmkmElementType; 

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, elementid, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, active, createdby, createdon
		, modifiedon
	}
	
	public EntTlTaskmst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmkmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmkmKeyid(String tmkmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmkmKeyid;
	}

	public String getTmkmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTmkmFlid(String tmkmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tmkmFlid;
	}

	public String getTmkmRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setTmkmRoleKeyid(String tmkmRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = tmkmRoleKeyid;
	}

	public String getTmkmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setTmkmElementid(String tmkmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = tmkmElementid;
	}

	public String getTmkmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmkmTempfield2(String tmkmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmkmTempfield2;
	}

	public String getTmkmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmkmTempfield3(String tmkmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmkmTempfield3;
	}

	public String getTmkmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmkmTempfield4(String tmkmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmkmTempfield4;
	}

	public String getTmkmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmkmTempfield5(String tmkmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmkmTempfield5;
	}

	public String getTmkmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTmkmTempfield6(String tmkmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = tmkmTempfield6;
	}

	public String getTmkmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setTmkmTempfield7(String tmkmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = tmkmTempfield7;
	}

	public String getTmkmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmkmActive(String tmkmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmkmActive;
	}

	public String getTmkmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmkmCreatedby(String tmkmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmkmCreatedby;
	}

	public String getTmkmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmkmCreatedon(String tmkmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmkmCreatedon;
	}

	public String getTmkmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmkmModifiedon(String tmkmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmkmModifiedon;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}

	public String getTmkmElementType() {
		return tmkmElementType;
	}

	public void setTmkmElementType(String tmkmElementType) {
		this.tmkmElementType = tmkmElementType;
	}

}

