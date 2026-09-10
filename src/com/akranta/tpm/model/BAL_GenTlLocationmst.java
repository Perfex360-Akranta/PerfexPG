package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlLocationmst {

	private  Object [] saveArray = null;
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  
	

	public enum   tableFldConstants
	{
		keyid, companyid, name, code, description, tempfield1, tempfield2
		, tempfield3, flid, active, createdby, createdon, modifiedon
	}

	public BAL_GenTlLocationmst()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getLocnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setLocnKeyid(String locnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = locnKeyid;
	}

	public String getLocnCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setLocnCompanyid(String locnCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = locnCompanyid;
	}

	public String getLocnName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setLocnName(String locnName) {
		saveArray[ tableFldConstants.name.ordinal() ] = locnName;
	}

	public String getLocnCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setLocnCode(String locnCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = locnCode;
	}

	public String getLocnDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setLocnDescription(String locnDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = locnDescription;
	}

	public String getLocnTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setLocnTempfield1(String locnTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = locnTempfield1;
	}

	public String getLocnTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setLocnTempfield2(String locnTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = locnTempfield2;
	}

	public String getLocnTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setLocnTempfield3(String locnTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = locnTempfield3;
	}
	public String getLocnFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setLocnFlid(String locnFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = locnFlid;
	}

	public String getLocnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setLocnActive(String locnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = locnActive;
	}

	public String getLocnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setLocnCreatedby(String locnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = locnCreatedby;
	}

	public String getLocnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setLocnCreatedon(String locnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = locnCreatedon;
	}

	public String getLocnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setLocnModifiedon(String locnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = locnModifiedon;
	}
	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}
}

