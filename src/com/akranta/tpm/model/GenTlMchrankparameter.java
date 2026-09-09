package com.akranta.tpm.model;
import java.util.List;

public class GenTlMchrankparameter {

	private  Object [] saveArray = null;  
	private List<GenTlMchrankparameter> Mchrankparameter ;

	public enum   tableFldConstants
	{
		keyid, parametername, slno, resultarea, maximummarks, isgrouped
		, active, createdby, createdon, modifiedon
	}

	public GenTlMchrankparameter()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getMrkpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMrkpKeyid(String mrkpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mrkpKeyid;
	}

	public String getMrkpParametername() {
		return (String) saveArray[ tableFldConstants.parametername.ordinal() ];
	}

	public void setMrkpParametername(String mrkpParametername) {
		saveArray[ tableFldConstants.parametername.ordinal() ] = mrkpParametername;
	}

	public String getMrkpSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setMrkpSlno(String mrkpSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = mrkpSlno;
	}

	public String getMrkpResultarea() {
		return (String) saveArray[ tableFldConstants.resultarea.ordinal() ];
	}

	public void setMrkpResultarea(String mrkpResultarea) {
		saveArray[ tableFldConstants.resultarea.ordinal() ] = mrkpResultarea;
	}

	public String getMrkpMaximummarks() {
		return (String) saveArray[ tableFldConstants.maximummarks.ordinal() ];
	}

	public void setMrkpMaximummarks(String mrkpMaximummarks) {
		saveArray[ tableFldConstants.maximummarks.ordinal() ] = mrkpMaximummarks;
	}

	public String getMrkpIsgrouped() {
		return (String) saveArray[ tableFldConstants.isgrouped.ordinal() ];
	}

	public void setMrkpIsgrouped(String mrkpIsgrouped) {
		saveArray[ tableFldConstants.isgrouped.ordinal() ] = mrkpIsgrouped;
	}

	public String getMrkpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMrkpActive(String mrkpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mrkpActive;
	}

	public String getMrkpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMrkpCreatedby(String mrkpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mrkpCreatedby;
	}

	public String getMrkpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMrkpCreatedon(String mrkpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mrkpCreatedon;
	}

	public String getMrkpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMrkpModifiedon(String mrkpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mrkpModifiedon;
	}
}

