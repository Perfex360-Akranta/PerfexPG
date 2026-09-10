package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlSbumst {

	private  Object [] saveArray = null;  
	
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  
	
	public enum   tableFldConstants
	{
		keyid, companyid, locationid, factoryid, flid, name, code, description
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public BAL_GenTlSbumst()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getSbutKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSbutKeyid(String sbutKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sbutKeyid;
	}

	public String getSbutCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setSbutCompanyid(String sbutCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = sbutCompanyid;
	}

	public String getSbutLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setSbutLocationid(String sbutLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = sbutLocationid;
	}

	public String getSbutFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setSbutFactoryid(String sbutFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = sbutFactoryid;
	}

	public String getSbutFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSbutFlid(String sbutFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sbutFlid;
	}

	public String getSbutName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setSbutName(String sbutName) {
		saveArray[ tableFldConstants.name.ordinal() ] = sbutName;
	}

	public String getSbutCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setSbutCode(String sbutCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = sbutCode;
	}

	public String getSbutDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setSbutDescription(String sbutDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = sbutDescription;
	}

	public String getSbutTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSbutTempfield1(String sbutTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = sbutTempfield1;
	}

	public String getSbutTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSbutTempfield2(String sbutTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sbutTempfield2;
	}

	public String getSbutTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSbutTempfield3(String sbutTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sbutTempfield3;
	}

	public String getSbutTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSbutTempfield4(String sbutTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sbutTempfield4;
	}

	public String getSbutActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSbutActive(String sbutActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sbutActive;
	}

	public String getSbutCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSbutCreatedby(String sbutCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sbutCreatedby;
	}

	public String getSbutCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSbutCreatedon(String sbutCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sbutCreatedon;
	}

	public String getSbutModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSbutModifiedon(String sbutModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sbutModifiedon;
	}
	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}

}

