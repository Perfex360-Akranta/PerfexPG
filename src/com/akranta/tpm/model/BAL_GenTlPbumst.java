package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlPbumst {

	private  Object [] saveArray = null;  
	
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  
	private String company;
	private String location;
	
	
	public enum   tableFldConstants
	{
		keyid, factoryid, sbuid, flid, name, code, description, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public BAL_GenTlPbumst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getPbutKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPbutKeyid(String pbutKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pbutKeyid;
	}

	public String getPbutFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPbutFactoryid(String pbutFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = pbutFactoryid;
	}

	public String getPbutSbuid() {
		return (String) saveArray[ tableFldConstants.sbuid.ordinal() ];
	}

	public void setPbutSbuid(String pbutSbuid) {
		saveArray[ tableFldConstants.sbuid.ordinal() ] = pbutSbuid;
	}

	public String getPbutFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPbutFlid(String pbutFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = pbutFlid;
	}

	public String getPbutName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setPbutName(String pbutName) {
		saveArray[ tableFldConstants.name.ordinal() ] = pbutName;
	}

	public String getPbutCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setPbutCode(String pbutCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = pbutCode;
	}

	public String getPbutDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setPbutDescription(String pbutDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = pbutDescription;
	}

	public String getPbutTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPbutTempfield1(String pbutTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pbutTempfield1;
	}

	public String getPbutTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPbutTempfield2(String pbutTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pbutTempfield2;
	}

	public String getPbutTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPbutTempfield3(String pbutTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pbutTempfield3;
	}

	public String getPbutTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPbutTempfield4(String pbutTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pbutTempfield4;
	}

	public String getPbutActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPbutActive(String pbutActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pbutActive;
	}

	public String getPbutCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPbutCreatedby(String pbutCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pbutCreatedby;
	}

	public String getPbutCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPbutCreatedon(String pbutCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pbutCreatedon;
	}

	public String getPbutModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPbutModifiedon(String pbutModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pbutModifiedon;
	}

	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

}

