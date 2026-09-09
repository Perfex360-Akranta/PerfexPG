package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlTemplategradelink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		templateid, gradeid, minimummarks, maximummarks, auditmasterid
		, active, createdby, createdon, modifiedon
	}

	public JhaTlTemplategradelink()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJtglTemplateid() {
		return (String) saveArray[ tableFldConstants.templateid.ordinal() ];
	}

	public void setJtglTemplateid(String jtglTemplateid) {
		saveArray[ tableFldConstants.templateid.ordinal() ] = jtglTemplateid;
	}

	public String getJtglGradeid() {
		return (String) saveArray[ tableFldConstants.gradeid.ordinal() ];
	}

	public void setJtglGradeid(String jtglGradeid) {
		saveArray[ tableFldConstants.gradeid.ordinal() ] = jtglGradeid;
	}

	public String getJtglMinimummarks() {
		return (String) saveArray[ tableFldConstants.minimummarks.ordinal() ];
	}

	public void setJtglMinimummarks(String jtglMinimummarks) {
		saveArray[ tableFldConstants.minimummarks.ordinal() ] = jtglMinimummarks;
	}

	public String getJtglMaximummarks() {
		return (String) saveArray[ tableFldConstants.maximummarks.ordinal() ];
	}

	public void setJtglMaximummarks(String jtglMaximummarks) {
		saveArray[ tableFldConstants.maximummarks.ordinal() ] = jtglMaximummarks;
	}

	public String getJtglAuditmasterid() {
		return (String) saveArray[ tableFldConstants.auditmasterid.ordinal() ];
	}

	public void setJtglAuditmasterid(String jtglAuditmasterid) {
		saveArray[ tableFldConstants.auditmasterid.ordinal() ] = jtglAuditmasterid;
	}

	public String getJtglActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJtglActive(String jtglActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jtglActive;
	}

	public String getJtglCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJtglCreatedby(String jtglCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jtglCreatedby;
	}

	public String getJtglCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJtglCreatedon(String jtglCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jtglCreatedon;
	}

	public String getJtglModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJtglModifiedon(String jtglModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jtglModifiedon;
	}

}

