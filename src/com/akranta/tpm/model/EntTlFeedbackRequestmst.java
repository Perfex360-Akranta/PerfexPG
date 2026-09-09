package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedbackRequestmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, prog_keyid, bach_keyid, request_date, request_by, request_remarks
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlFeedbackRequestmst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFrqmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFrqmKeyid(String frqmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = frqmKeyid;
	}

	public String getFrqmProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setFrqmProgKeyid(String frqmProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = frqmProgKeyid;
	}

	public String getFrqmBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setFrqmBachKeyid(String frqmBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = frqmBachKeyid;
	}

	public String getFrqmRequestDate() {
		return (String) saveArray[ tableFldConstants.request_date.ordinal() ];
	}

	public void setFrqmRequestDate(String frqmRequestDate) {
		saveArray[ tableFldConstants.request_date.ordinal() ] = frqmRequestDate;
	}

	public String getFrqmRequestBy() {
		return (String) saveArray[ tableFldConstants.request_by.ordinal() ];
	}

	public void setFrqmRequestBy(String frqmRequestBy) {
		saveArray[ tableFldConstants.request_by.ordinal() ] = frqmRequestBy;
	}

	public String getFrqmRequestRemarks() {
		return (String) saveArray[ tableFldConstants.request_remarks.ordinal() ];
	}

	public void setFrqmRequestRemarks(String frqmRequestRemarks) {
		saveArray[ tableFldConstants.request_remarks.ordinal() ] = frqmRequestRemarks;
	}

	public String getFrqmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFrqmTempfield1(String frqmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = frqmTempfield1;
	}

	public String getFrqmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFrqmTempfield2(String frqmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = frqmTempfield2;
	}

	public String getFrqmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFrqmTempfield3(String frqmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = frqmTempfield3;
	}

	public String getFrqmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFrqmTempfield4(String frqmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = frqmTempfield4;
	}

	public String getFrqmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFrqmTempfield5(String frqmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = frqmTempfield5;
	}

	public String getFrqmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFrqmActive(String frqmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = frqmActive;
	}

	public String getFrqmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFrqmCreatedby(String frqmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = frqmCreatedby;
	}

	public String getFrqmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFrqmCreatedon(String frqmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = frqmCreatedon;
	}

	public String getFrqmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFrqmModifiedon(String frqmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = frqmModifiedon;
	}

}

