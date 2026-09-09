package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFacultymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, name, type, fact_keyid, empm_keyid, potential, address1
		, address2, address3, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlFacultymst()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFtymKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFtymKeyid(String ftymKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ftymKeyid;
	}

	public String getFtymCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setFtymCode(String ftymCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = ftymCode;
	}

	public String getFtymName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setFtymName(String ftymName) {
		saveArray[ tableFldConstants.name.ordinal() ] = ftymName;
	}

	public String getFtymType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setFtymType(String ftymType) {
		saveArray[ tableFldConstants.type.ordinal() ] = ftymType;
	}

	public String getFtymFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setFtymFactKeyid(String ftymFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = ftymFactKeyid;
	}

	public String getFtymEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setFtymEmpmKeyid(String ftymEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = ftymEmpmKeyid;
	}

	public String getFtymPotential() {
		return (String) saveArray[ tableFldConstants.potential.ordinal() ];
	}

	public void setFtymPotential(String ftymPotential) {
		saveArray[ tableFldConstants.potential.ordinal() ] = ftymPotential;
	}

	public String getFtymAddress1() {
		return (String) saveArray[ tableFldConstants.address1.ordinal() ];
	}

	public void setFtymAddress1(String ftymAddress1) {
		saveArray[ tableFldConstants.address1.ordinal() ] = ftymAddress1;
	}

	public String getFtymAddress2() {
		return (String) saveArray[ tableFldConstants.address2.ordinal() ];
	}

	public void setFtymAddress2(String ftymAddress2) {
		saveArray[ tableFldConstants.address2.ordinal() ] = ftymAddress2;
	}

	public String getFtymAddress3() {
		return (String) saveArray[ tableFldConstants.address3.ordinal() ];
	}

	public void setFtymAddress3(String ftymAddress3) {
		saveArray[ tableFldConstants.address3.ordinal() ] = ftymAddress3;
	}

	public String getFtymRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setFtymRemarks(String ftymRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = ftymRemarks;
	}

	public String getFtymTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFtymTempfield1(String ftymTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = ftymTempfield1;
	}

	public String getFtymTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFtymTempfield2(String ftymTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ftymTempfield2;
	}

	public String getFtymTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFtymTempfield3(String ftymTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ftymTempfield3;
	}

	public String getFtymTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFtymTempfield4(String ftymTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = ftymTempfield4;
	}

	public String getFtymTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFtymTempfield5(String ftymTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = ftymTempfield5;
	}

	public String getFtymActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFtymActive(String ftymActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ftymActive;
	}

	public String getFtymCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFtymCreatedby(String ftymCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ftymCreatedby;
	}

	public String getFtymCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFtymCreatedon(String ftymCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ftymCreatedon;
	}

	public String getFtymModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFtymModifiedon(String ftymModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ftymModifiedon;
	}

}

