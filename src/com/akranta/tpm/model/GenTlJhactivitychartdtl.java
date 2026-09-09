package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlJhactivitychartdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, achm_keyid, shiftid, description, time, remarks, tempfield1
		, tempfield2, createdby, createdon, modifiedon, tempfield3, jacdtempfield4
		, tempfield5, active
	}

	public GenTlJhactivitychartdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJacdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJacdKeyid(String jacdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jacdKeyid;
	}

	public String getJacdAchmKeyid() {
		return (String) saveArray[ tableFldConstants.achm_keyid.ordinal() ];
	}

	public void setJacdAchmKeyid(String jacdAchmKeyid) {
		saveArray[ tableFldConstants.achm_keyid.ordinal() ] = jacdAchmKeyid;
	}

	public String getJacdShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setJacdShiftid(String jacdShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = jacdShiftid;
	}

	public String getJacdDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setJacdDescription(String jacdDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = jacdDescription;
	}

	public String getJacdTime() {
		return (String) saveArray[ tableFldConstants.time.ordinal() ];
	}

	public void setJacdTime(String jacdTime) {
		saveArray[ tableFldConstants.time.ordinal() ] = jacdTime;
	}

	public String getJacdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setJacdRemarks(String jacdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = jacdRemarks;
	}

	public String getJacdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setJacdTempfield1(String jacdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = jacdTempfield1;
	}

	public String getJacdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setJacdTempfield2(String jacdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = jacdTempfield2;
	}

	public String getJacdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJacdCreatedby(String jacdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jacdCreatedby;
	}

	public String getJacdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJacdCreatedon(String jacdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jacdCreatedon;
	}

	public String getJacdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJacdModifiedon(String jacdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jacdModifiedon;
	}

	public String getJacdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJacdTempfield3(String jacdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jacdTempfield3;
	}

	public String getJacdtempfield4() {
		return (String) saveArray[ tableFldConstants.jacdtempfield4.ordinal() ];
	}

	public void setJacdtempfield4(String jacdtempfield4) {
		saveArray[ tableFldConstants.jacdtempfield4.ordinal() ] = jacdtempfield4;
	}

	public String getJacdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJacdTempfield5(String jacdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jacdTempfield5;
	}

	public String getJacdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJacdActive(String jacdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jacdActive;
	}

}

