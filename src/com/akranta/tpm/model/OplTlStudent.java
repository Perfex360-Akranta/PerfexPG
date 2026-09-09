package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class OplTlStudent {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, oplm_keyid, date, teacher, student, mtrx_keyid, tempfield1
		, tempfield2, tempfield3, tempfield4, createdby, active, createdon
		, modifiedon
	}

	
	public OplTlStudent()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOpllKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOpllKeyid(String opllKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = opllKeyid;
	}

	public String getOpllOplmKeyid() {
		return (String) saveArray[ tableFldConstants.oplm_keyid.ordinal() ];
	}

	public void setOpllOplmKeyid(String opllOplmKeyid) {
		saveArray[ tableFldConstants.oplm_keyid.ordinal() ] = opllOplmKeyid;
	}

	public String getOpllDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOpllDate(String opllDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = opllDate;
	}

	public String getOpllTeacher() {
		return (String) saveArray[ tableFldConstants.teacher.ordinal() ];
	}

	public void setOpllTeacher(String opllTeacher) {
		saveArray[ tableFldConstants.teacher.ordinal() ] = opllTeacher;
	}

	public String getOpllStudent() {
		return (String) saveArray[ tableFldConstants.student.ordinal() ];
	}

	public void setOpllStudent(String opllStudent) {
		saveArray[ tableFldConstants.student.ordinal() ] = opllStudent;
	}

	public String getOpllMtrxKeyid() {
		return (String) saveArray[ tableFldConstants.mtrx_keyid.ordinal() ];
	}

	public void setOpllMtrxKeyid(String opllMtrxKeyid) {
		saveArray[ tableFldConstants.mtrx_keyid.ordinal() ] = opllMtrxKeyid;
	}

	public String getOpllTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOpllTempfield1(String opllTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = opllTempfield1;
	}

	public String getOpllTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOpllTempfield2(String opllTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = opllTempfield2;
	}

	public String getOpllTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOpllTempfield3(String opllTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = opllTempfield3;
	}

	public String getOpllTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOpllTempfield4(String opllTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = opllTempfield4;
	}

	public String getOpllCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOpllCreatedby(String opllCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = opllCreatedby;
	}

	public String getOpllActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOpllActive(String opllActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = opllActive;
	}

	public String getOpllCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOpllCreatedon(String opllCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = opllCreatedon;
	}

	public String getOpllModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOpllModifiedon(String opllModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = opllModifiedon;
	}

}

