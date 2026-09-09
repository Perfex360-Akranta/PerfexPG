package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class Fishbone {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		no, name, parentid, levelno, active, createdby, createdon, modifiedon
	}

	public Fishbone()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFiboNo() {
		return (String) saveArray[ tableFldConstants.no.ordinal() ];
	}

	public void setFiboNo(String fiboNo) {
		saveArray[ tableFldConstants.no.ordinal() ] = fiboNo;
	}

	public String getFiboName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setFiboName(String fiboName) {
		saveArray[ tableFldConstants.name.ordinal() ] = fiboName;
	}

	public String getFiboParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFiboParentid(String fiboParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fiboParentid;
	}

	public String getFiboLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setFiboLevelno(String fiboLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = fiboLevelno;
	}

	public String getFiboActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFiboActive(String fiboActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fiboActive;
	}

	public String getFiboCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFiboCreatedby(String fiboCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fiboCreatedby;
	}

	public String getFiboCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFiboCreatedon(String fiboCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fiboCreatedon;
	}

	public String getFiboModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFiboModifiedon(String fiboModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fiboModifiedon;
	}

}

