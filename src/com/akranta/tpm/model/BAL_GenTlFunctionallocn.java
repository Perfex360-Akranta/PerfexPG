package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlFunctionallocn {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, description, elementtype
		, active,keyid
	}

	public BAL_GenTlFunctionallocn()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFnlnOriginalid() {
		return (String) saveArray[ tableFldConstants.originalid.ordinal() ];
	}

	public void setFnlnOriginalid(String fnlnOriginalid) {
		saveArray[ tableFldConstants.originalid.ordinal() ] = fnlnOriginalid;
	}

	public String getFnlnElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setFnlnElementid(String fnlnElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = fnlnElementid;
	}

	public String getFnlnParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFnlnParentid(String fnlnParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fnlnParentid;
	}

	public String getFnlnDisplaycode() {
		return (String) saveArray[ tableFldConstants.displaycode.ordinal() ];
	}

	public void setFnlnDisplaycode(String fnlnDisplaycode) {
		saveArray[ tableFldConstants.displaycode.ordinal() ] = fnlnDisplaycode;
	}

	public String getFnlnDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setFnlnDescription(String fnlnDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = fnlnDescription;
	}

	public String getFnlnElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setFnlnElementtype(String fnlnElementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = fnlnElementtype;
	}

	public String getFnlnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFnlnActive(String fnlnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fnlnActive;
	}
	
	public String getFnlnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFnlnKeyid(String fnlnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fnlnKeyid;
	}

}

