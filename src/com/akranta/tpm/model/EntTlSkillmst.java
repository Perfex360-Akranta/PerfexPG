package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSkillmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, name, parentid, ischild, fact_keyid
		, dept_keyid,cell_function,cell_fun_keyid
		, evaluationtypeid,type, remarks, effective_date, inactive_date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlSkillmst()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getSkilKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSkilKeyid(String skilKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = skilKeyid;
	}

	public String getSkilCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setSkilCode(String skilCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = skilCode;
	}

	public String getSkilName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setSkilName(String skilName) {
		saveArray[ tableFldConstants.name.ordinal() ] = skilName;
	}

	public String getSkilParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setSkilParentid(String skilParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = skilParentid;
	}

	public String getSkilIschild() {
		return (String) saveArray[ tableFldConstants.ischild.ordinal() ];
	}

	public void setSkilIschild(String skilIschild) {
		saveArray[ tableFldConstants.ischild.ordinal() ] = skilIschild;
	}

	public String getSkilFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setSkilFactKeyid(String skilFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = skilFactKeyid;
	}
	
	public String getSkilDeptKeyid() {
		return (String) saveArray[ tableFldConstants.dept_keyid.ordinal() ];
	}

	public void setSkilDeptKeyid(String skilDeptKeyid) {
		saveArray[ tableFldConstants.dept_keyid.ordinal() ] = skilDeptKeyid;
	}	
	
	public String getSkilCellFunction() {
		return (String) saveArray[ tableFldConstants.cell_function.ordinal() ];
	}

	public void setSkilCellFunction(String skilCellFunction) {
		saveArray[ tableFldConstants.cell_function.ordinal() ] = skilCellFunction;
	}	
	
	public String getSkilCellFunKeyid() {
		return (String) saveArray[ tableFldConstants.cell_fun_keyid.ordinal() ];
	}

	public void setSkilCellFunKeyid(String skilCellFunKeyId) {
		saveArray[ tableFldConstants.cell_fun_keyid.ordinal() ] = skilCellFunKeyId;
	}	
	
	public String getSkilEvaluationtypeid() {
		return (String) saveArray[ tableFldConstants.evaluationtypeid.ordinal() ];
	}

	public void setSkilEvaluationtypeid(String skilEvaluationtypeid) {
		saveArray[ tableFldConstants.evaluationtypeid.ordinal() ] = skilEvaluationtypeid;
	}
	
	public String getSkilType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSkilType(String skilType) {
		saveArray[ tableFldConstants.type.ordinal() ] = skilType;
	}

	public String getSkilRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSkilRemarks(String skilRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = skilRemarks;
	}

	public String getSkilEffectiveDate() {
		return (String) saveArray[ tableFldConstants.effective_date.ordinal() ];
	}

	public void setSkilEffectiveDate(String skilEffectiveDate) {
		saveArray[ tableFldConstants.effective_date.ordinal() ] = skilEffectiveDate;
	}

	public String getSkilInactiveDate() {
		return (String) saveArray[ tableFldConstants.inactive_date.ordinal() ];
	}

	public void setSkilInactiveDate(String skilInactiveDate) {
		saveArray[ tableFldConstants.inactive_date.ordinal() ] = skilInactiveDate;
	}

	public String getSkilTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSkilTempfield1(String skilTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = skilTempfield1;
	}

	public String getSkilTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSkilTempfield2(String skilTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = skilTempfield2;
	}

	public String getSkilTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSkilTempfield3(String skilTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = skilTempfield3;
	}

	public String getSkilTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSkilTempfield4(String skilTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = skilTempfield4;
	}

	public String getSkilTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSkilTempfield5(String skilTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = skilTempfield5;
	}

	public String getSkilActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSkilActive(String skilActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = skilActive;
	}

	public String getSkilCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSkilCreatedby(String skilCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = skilCreatedby;
	}

	public String getSkilCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSkilCreatedon(String skilCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = skilCreatedon;
	}

	public String getSkilModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSkilModifiedon(String skilModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = skilModifiedon;
	}

}

