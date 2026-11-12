package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlActionplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, description, created_date, created_by, resp_dept
		, responsiblity, target_date, status, remarks, completed_by, completed_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
	}

	public SheTlActionplan()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getSacpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSacpKeyid(String sacpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sacpKeyid;
	}

	public String getSacpSincKeyid() {
		return (String) saveArray[ tableFldConstants.sinc_keyid.ordinal() ];
	}

	public void setSacpSincKeyid(String sacpSincKeyid) {
		saveArray[ tableFldConstants.sinc_keyid.ordinal() ] = sacpSincKeyid;
	}

	public String getSacpDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setSacpDescription(String sacpDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = sacpDescription;
	}

	public String getSacpCreatedDate() {
		return (String) saveArray[ tableFldConstants.created_date.ordinal() ];
	}

	public void setSacpCreatedDate(String sacpCreatedDate) {
		saveArray[ tableFldConstants.created_date.ordinal() ] = sacpCreatedDate;
	}

	public String getSacpCreatedBy() {
		return (String) saveArray[ tableFldConstants.created_by.ordinal() ];
	}

	public void setSacpCreatedBy(String sacpCreatedBy) {
		saveArray[ tableFldConstants.created_by.ordinal() ] = sacpCreatedBy;
	}

	public String getSacpRespDept() {
		return (String) saveArray[ tableFldConstants.resp_dept.ordinal() ];
	}

	public void setSacpRespDept(String sacpRespDept) {
		saveArray[ tableFldConstants.resp_dept.ordinal() ] = sacpRespDept;
	}

	public String getSacpResponsiblity() {
		return (String) saveArray[ tableFldConstants.responsiblity.ordinal() ];
	}

	public void setSacpResponsiblity(String sacpResponsiblity) {
		saveArray[ tableFldConstants.responsiblity.ordinal() ] = sacpResponsiblity;
	}

	public String getSacpTargetDate() {
		return (String) saveArray[ tableFldConstants.target_date.ordinal() ];
	}

	public void setSacpTargetDate(String sacpTargetDate) {
		saveArray[ tableFldConstants.target_date.ordinal() ] = sacpTargetDate;
	}

	public String getSacpStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSacpStatus(String sacpStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = sacpStatus;
	}

	public String getSacpRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSacpRemarks(String sacpRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = sacpRemarks;
	}

	public String getSacpCompletedBy() {
		return (String) saveArray[ tableFldConstants.completed_by.ordinal() ];
	}

	public void setSacpCompletedBy(String sacpCompletedBy) {
		saveArray[ tableFldConstants.completed_by.ordinal() ] = sacpCompletedBy;
	}

	public String getSacpCompletedDate() {
		return (String) saveArray[ tableFldConstants.completed_date.ordinal() ];
	}

	public void setSacpCompletedDate(String sacpCompletedDate) {
		saveArray[ tableFldConstants.completed_date.ordinal() ] = sacpCompletedDate;
	}

	public String getSacpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSacpTempfield1(String sacpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = sacpTempfield1;
	}

	public String getSacpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSacpTempfield2(String sacpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sacpTempfield2;
	}

	public String getSacpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSacpTempfield3(String sacpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sacpTempfield3;
	}

	public String getSacpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSacpTempfield4(String sacpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sacpTempfield4;
	}

	public String getSacpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSacpTempfield5(String sacpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sacpTempfield5;
	}

	public String getSacpTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSacpTempfield6(String sacpTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = sacpTempfield6;
	}

	public String getSacpTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setSacpTempfield7(String sacpTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = sacpTempfield7;
	}

	public String getSacpTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setSacpTempfield8(String sacpTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = sacpTempfield8;
	}

	public String getSacpTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setSacpTempfield9(String sacpTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = sacpTempfield9;
	}

	public String getSacpTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setSacpTempfield10(String sacpTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = sacpTempfield10;
	}

}

