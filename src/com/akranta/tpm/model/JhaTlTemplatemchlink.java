package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlTemplatemchlink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		templateid, machineid, active, createdby, createdon, modtimestamp
	}

	public JhaTlTemplatemchlink()
	{
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJtmlTemplateid() {
		return (String) saveArray[ tableFldConstants.templateid.ordinal() ];
	}

	public void setJtmlTemplateid(String jtmlTemplateid) {
		saveArray[ tableFldConstants.templateid.ordinal() ] = jtmlTemplateid;
	}

	public String getJtmlMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setJtmlMachineid(String jtmlMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = jtmlMachineid;
	}

	public String getJtmlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJtmlActive(String jtmlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jtmlActive;
	}

	public String getJtmlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJtmlCreatedby(String jtmlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jtmlCreatedby;
	}

	public String getJtmlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJtmlCreatedon(String jtmlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jtmlCreatedon;
	}

	public String getJtmlModtimestamp() {
		return (String) saveArray[ tableFldConstants.modtimestamp.ordinal() ];
	}

	public void setJtmlModtimestamp(String jtmlModtimestamp) {
		saveArray[ tableFldConstants.modtimestamp.ordinal() ] = jtmlModtimestamp;
	}

}

