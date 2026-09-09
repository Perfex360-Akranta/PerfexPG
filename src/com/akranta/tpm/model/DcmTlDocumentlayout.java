package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DcmTlDocumentlayout {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, parentid, levelno, displayorder, isfileavl, isparent
		, active, createdby, createdon, modifiedon
	}

	public DcmTlDocumentlayout()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	public String getDmlyKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmlyKeyid(String dmlyKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmlyKeyid;
	}

	public String getDmlyName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setDmlyName(String dmlyName) {
		saveArray[ tableFldConstants.name.ordinal() ] = dmlyName;
	}

	public String getDmlyParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setDmlyParentid(String dmlyParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = dmlyParentid;
	}

	public String getDmlyLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setDmlyLevelno(String dmlyLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = dmlyLevelno;
	}

	public String getDmlyDisplayorder() {
		return (String) saveArray[ tableFldConstants.displayorder.ordinal() ];
	}

	public void setDmlyDisplayorder(String dmlyDisplayorder) {
		saveArray[ tableFldConstants.displayorder.ordinal() ] = dmlyDisplayorder;
	}

	public String getDmlyIsfileavl() {
		return (String) saveArray[ tableFldConstants.isfileavl.ordinal() ];
	}

	public void setDmlyIsfileavl(String dmlyIsfileavl) {
		saveArray[ tableFldConstants.isfileavl.ordinal() ] = dmlyIsfileavl;
	}

	public String getDmlyIsparent() {
		return (String) saveArray[ tableFldConstants.isparent.ordinal() ];
	}

	public void setDmlyIsparent(String dmlyIsparent) {
		saveArray[ tableFldConstants.isparent.ordinal() ] = dmlyIsparent;
	}

	public String getDmlyActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmlyActive(String dmlyActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmlyActive;
	}

	public String getDmlyCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmlyCreatedby(String dmlyCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmlyCreatedby;
	}

	public String getDmlyCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmlyCreatedon(String dmlyCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmlyCreatedon;
	}

	public String getDmlyModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmlyModifiedon(String dmlyModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmlyModifiedon;
	}

}

