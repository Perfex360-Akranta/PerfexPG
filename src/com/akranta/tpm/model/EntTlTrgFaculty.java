package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrgFaculty {

	private  Object [] saveArray = null;  
	private String isFacultyLink;
    
	public enum   tableFldConstants
	{
		keyid, etcm_keyid, etcm_flid, facultyid, facultytype, dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlTrgFaculty()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtcfKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcfKeyid(String etcfKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcfKeyid;
	}

	public String getEtcfEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.etcm_keyid.ordinal() ];
	}

	public void setEtcfEtcmKeyid(String etcfEtcmKeyid) {
		saveArray[ tableFldConstants.etcm_keyid.ordinal() ] = etcfEtcmKeyid;
	}

	public String getEtcfEtcmFlid() {
		return (String) saveArray[ tableFldConstants.etcm_flid.ordinal() ];
	}

	public void setEtcfEtcmFlid(String etcfEtcmFlid) {
		saveArray[ tableFldConstants.etcm_flid.ordinal() ] = etcfEtcmFlid;
	}

	public String getEtcfFacultyId() {
		return (String) saveArray[ tableFldConstants.facultyid.ordinal() ];
	}

	public void setEtcfFacultyId(String etcfFacultyid) {
		saveArray[ tableFldConstants.facultyid.ordinal() ] = etcfFacultyid;
	}

	public String getEtcfFacultytype() {
		return (String) saveArray[ tableFldConstants.facultytype.ordinal() ];
	}

	public void setEtcfFacultytype(String etcfFacultytype) {
		saveArray[ tableFldConstants.facultytype.ordinal() ] = etcfFacultytype;
	}
	
	public String getEtcfDateadd() {
		return (String) saveArray[ tableFldConstants.dateadd.ordinal() ];
	}

	public void setEtcfDateadd(String etcfDateadd) {
		saveArray[ tableFldConstants.dateadd.ordinal() ] = etcfDateadd;
	}

	public String getEtcfTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtcfTempfield1(String etcfTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etcfTempfield1;
	}

	public String getEtcfTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtcfTempfield2(String etcfTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etcfTempfield2;
	}
	
	
	public String getEtcfTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtcfTempfield3(String etcfTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etcfTempfield3;
	}
	
	public String getEtcfTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtcfTempfield4(String etcfTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etcfTempfield4;
	}
	
	public String getEtcfTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtcfTempfield5(String etcfTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etcfTempfield5;
	}


	public String getEtcfActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtcfActive(String etcfActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcfActive;
	}

	public String getEtcfCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEtcfCreatedby(String etcfCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etcfCreatedby;
	}

	public String getEtcfCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtcfCreatedon(String etcfCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcfCreatedon;
	}

	public String getEtcfModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtcfModifiedon(String etcfModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcfModifiedon;
	}
	public String getIsFacultyLink() {
		return isFacultyLink;
	}

	public void setIsFacultyLink(String isFacultyLink) {
		this.isFacultyLink = isFacultyLink;
	}



}

