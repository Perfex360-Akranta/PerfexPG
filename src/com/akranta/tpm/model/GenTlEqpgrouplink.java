package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEqpgrouplink {

	private  Object [] saveArray = null;  
	private String txtmachineId=null;

	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, elementtype, active
	}

	public GenTlEqpgrouplink()
	{
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEqglOriginalid() {
		return (String) saveArray[ tableFldConstants.originalid.ordinal() ];
	}

	public void setEqglOriginalid(String eqglOriginalid) {
		saveArray[ tableFldConstants.originalid.ordinal() ] = eqglOriginalid;
	}

	public String getEqglElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setEqglElementid(String eqglElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = eqglElementid;
	}

	public String getEqglParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setEqglParentid(String eqglParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = eqglParentid;
	}

	public String getEqglDisplaycode() {
		return (String) saveArray[ tableFldConstants.displaycode.ordinal() ];
	}

	public void setEqglDisplaycode(String eqglDisplaycode) {
		saveArray[ tableFldConstants.displaycode.ordinal() ] = eqglDisplaycode;
	}

	public String getEqglElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setEqglElementtype(String eqglElementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = eqglElementtype;
	}

	public String getEqglActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEqglActive(String eqglActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eqglActive;
	}

	public void setTxtmachineId(String txtmachineId) {
		this.txtmachineId = txtmachineId;
	}

	public String getTxtmachineId() {
		return txtmachineId;
	}

}

