package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrainingmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, title, date, duration, faculty, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, flid, active
		, createdby, createdon, modifiedon
	}

	public EntTlTrainingmst()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmstKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmstKeyid(String tmstKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmstKeyid;
	}

	public String getTmstTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setTmstTitle(String tmstTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = tmstTitle;
	}

	public String getTmstDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setTmstDate(String tmstDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = tmstDate;
	}

	public String getTmstDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setTmstDuration(String tmstDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = tmstDuration;
	}

	public String getTmstFaculty() {
		return (String) saveArray[ tableFldConstants.faculty.ordinal() ];
	}

	public void setTmstFaculty(String tmstFaculty) {
		saveArray[ tableFldConstants.faculty.ordinal() ] = tmstFaculty;
	}

	public String getTmstTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTmstTempfield1(String tmstTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tmstTempfield1;
	}

	public String getTmstTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmstTempfield2(String tmstTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmstTempfield2;
	}

	public String getTmstTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmstTempfield3(String tmstTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmstTempfield3;
	}

	public String getTmstTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmstTempfield4(String tmstTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmstTempfield4;
	}

	public String getTmstTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmstTempfield5(String tmstTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmstTempfield5;
	}

	public String getTmstTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTmstTempfield6(String tmstTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = tmstTempfield6;
	}

	public String getTmstFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTmstFlid(String tmstFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tmstFlid;
	}

	public String getTmstActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmstActive(String tmstActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmstActive;
	}

	public String getTmstCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmstCreatedby(String tmstCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmstCreatedby;
	}

	public String getTmstCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmstCreatedon(String tmstCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmstCreatedon;
	}

	public String getTmstModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmstModifiedon(String tmstModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmstModifiedon;
	}

	public void setSaveArray(Object[] saveArray) 
	{
		this.saveArray = saveArray;
		
	}

}

