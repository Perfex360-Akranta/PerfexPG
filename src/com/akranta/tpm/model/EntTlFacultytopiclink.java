package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFacultytopiclink {

	private  Object [] saveArray = null;  
	
    private String isFacultyLink;
	public enum   tableFldConstants
	{
		keyid, facultyid, topicid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, active, createdby, createdon
		, modifiedon
	}

	public EntTlFacultytopiclink()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFtlkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFtlkKeyid(String ftlkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ftlkKeyid;
	}

	public String getFtlkFacultyid() {
		return (String) saveArray[ tableFldConstants.facultyid.ordinal() ];
	}

	public void setFtlkFacultyid(String ftlkFacultyid) {
		saveArray[ tableFldConstants.facultyid.ordinal() ] = ftlkFacultyid;
	}

	public String getFtlkTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setFtlkTopicid(String ftlkTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = ftlkTopicid;
	}

	public String getFtlkTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFtlkTempfield1(String ftlkTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = ftlkTempfield1;
	}

	public String getFtlkTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFtlkTempfield2(String ftlkTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ftlkTempfield2;
	}

	public String getFtlkTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFtlkTempfield3(String ftlkTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ftlkTempfield3;
	}

	public String getFtlkTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFtlkTempfield4(String ftlkTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = ftlkTempfield4;
	}

	public String getFtlkTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFtlkTempfield5(String ftlkTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = ftlkTempfield5;
	}

	public String getFtlkTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setFtlkTempfield6(String ftlkTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = ftlkTempfield6;
	}

	public String getFtlkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFtlkActive(String ftlkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ftlkActive;
	}

	public String getFtlkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFtlkCreatedby(String ftlkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ftlkCreatedby;
	}

	public String getFtlkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFtlkCreatedon(String ftlkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ftlkCreatedon;
	}

	public String getFtlkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFtlkModifiedon(String ftlkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ftlkModifiedon;
	}

	public String getIsFacultyLink() {
		return isFacultyLink;
	}

	public void setIsFacultyLink(String isFacultyLink) {
		this.isFacultyLink = isFacultyLink;
	}

}

