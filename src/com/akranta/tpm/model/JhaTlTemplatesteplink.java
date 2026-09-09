package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlTemplatesteplink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		templateid, jhstepid, active, createdby, createdon, modifiedon
	}

	public JhaTlTemplatesteplink()
	{
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJtslTemplateid() {
		return (String) saveArray[ tableFldConstants.templateid.ordinal() ];
	}

	public void setJtslTemplateid(String jtslTemplateid) {
		saveArray[ tableFldConstants.templateid.ordinal() ] = jtslTemplateid;
	}

	public String getJtslJhstepid() {
		return (String) saveArray[ tableFldConstants.jhstepid.ordinal() ];
	}

	public void setJtslJhstepid(String jtslJhstepid) {
		saveArray[ tableFldConstants.jhstepid.ordinal() ] = jtslJhstepid;
	}

	public String getJtslActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJtslActive(String jtslActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jtslActive;
	}

	public String getJtslCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJtslCreatedby(String jtslCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jtslCreatedby;
	}

	public String getJtslCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJtslCreatedon(String jtslCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jtslCreatedon;
	}

	public String getJtslModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJtslModifiedon(String jtslModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jtslModifiedon;
	}

}

