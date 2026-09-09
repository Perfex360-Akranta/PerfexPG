package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;
import com.akranta.tpm.model.GenTlLocationmst.tableFldConstants;

public class GenTlSectionmst {

	private  Object [] saveArray = null;
	private GenTlFunctionallocn genTlFunctionallocn;  
	private String sbu;
	private String location;
	

	public enum   tableFldConstants
	{
		keyid, factoryid, companyid, sectiongroup, code, name, flid, active
		, createdby, createdon, modifiedon
	}

	public GenTlSectionmst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getSectKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSectKeyid(String sectKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sectKeyid;
	}

	public String getSectFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setSectFactoryid(String sectFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = sectFactoryid;
	}

	public String getSectCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setSectCompanyid(String sectCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = sectCompanyid;
	}

	public String getSectSectiongroup() {
		return (String) saveArray[ tableFldConstants.sectiongroup.ordinal() ];
	}

	public void setSectSectiongroup(String sectSectiongroup) {
		saveArray[ tableFldConstants.sectiongroup.ordinal() ] = sectSectiongroup;
	}

	public String getSectCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setSectCode(String sectCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = sectCode;
	}

	public String getSectName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setSectName(String sectName) {
		saveArray[ tableFldConstants.name.ordinal() ] = sectName;
	}
	public String getSectFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSectFlid(String sectFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sectFlid;
	}
	public String getSectActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSectActive(String sectActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sectActive;
	}

	public String getSectCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSectCreatedby(String sectCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sectCreatedby;
	}

	public String getSectCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSectCreatedon(String sectCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sectCreatedon;
	}

	public String getSectModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSectModifiedon(String sectModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sectModifiedon;
	}

	public void setGenTlFunctionallocn(GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getSbu() {
		return sbu;
	}
	

}

