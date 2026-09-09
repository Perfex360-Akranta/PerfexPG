package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedbackparammst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, description, parentid, effective_date, inactive_date
		, type, remarks, displayorder, ischild, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlFeedbackparammst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getFbpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFbpmKeyid(String fbpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fbpmKeyid;
	}

	public String getFbpmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setFbpmCode(String fbpmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = fbpmCode;
	}

	public String getFbpmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setFbpmDescription(String fbpmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = fbpmDescription;
	}

	public String getFbpmParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFbpmParentid(String fbpmParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fbpmParentid;
	}

	public String getFbpmEffectiveDate() {
		return (String) saveArray[ tableFldConstants.effective_date.ordinal() ];
	}

	public void setFbpmEffectiveDate(String fbpmEffectiveDate) {
		saveArray[ tableFldConstants.effective_date.ordinal() ] = fbpmEffectiveDate;
	}

	public String getFbpmInactiveDate() {
		return (String) saveArray[ tableFldConstants.inactive_date.ordinal() ];
	}

	public void setFbpmInactiveDate(String fbpmInactiveDate) {
		saveArray[ tableFldConstants.inactive_date.ordinal() ] = fbpmInactiveDate;
	}

	public String getFbpmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setFbpmType(String fbpmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = fbpmType;
	}

	public String getFbpmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setFbpmRemarks(String fbpmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = fbpmRemarks;
	}

	public String getFbpmDisplayorder() {
		return (String) saveArray[ tableFldConstants.displayorder.ordinal() ];
	}

	public void setFbpmDisplayorder(String fbpmDisplayorder) {
		saveArray[ tableFldConstants.displayorder.ordinal() ] = fbpmDisplayorder;
	}

	public String getFbpmIschild() {
		return (String) saveArray[ tableFldConstants.ischild.ordinal() ];
	}

	public void setFbpmIschild(String fbpmIschild) {
		saveArray[ tableFldConstants.ischild.ordinal() ] = fbpmIschild;
	}

	public String getFbpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFbpmTempfield1(String fbpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fbpmTempfield1;
	}

	public String getFbpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFbpmTempfield2(String fbpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fbpmTempfield2;
	}

	public String getFbpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFbpmTempfield3(String fbpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fbpmTempfield3;
	}

	public String getFbpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFbpmTempfield4(String fbpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fbpmTempfield4;
	}

	public String getFbpmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFbpmTempfield5(String fbpmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fbpmTempfield5;
	}

	public String getFbpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFbpmActive(String fbpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fbpmActive;
	}

	public String getFbpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFbpmCreatedby(String fbpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fbpmCreatedby;
	}

	public String getFbpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFbpmCreatedon(String fbpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fbpmCreatedon;
	}

	public String getFbpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFbpmModifiedon(String fbpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fbpmModifiedon;
	}

}

