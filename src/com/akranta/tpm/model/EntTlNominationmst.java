package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlNominationmst {

	private  Object [] saveArray = null;  
	private List<EntTlNominationdtl> entTlNominationdtlList= null; 

	public enum   tableFldConstants
	{
		keyid, type, prog_keyid, bach_keyid, prepared_by, prepared_date
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlNominationmst()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	
	public List<EntTlNominationdtl> getNominationdtls() {
		return entTlNominationdtlList;
	}

	public void setNominationdtls(List<EntTlNominationdtl> entTlNominationdtlList) {
		this.entTlNominationdtlList = entTlNominationdtlList;
	}

	public String getNommKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNommKeyid(String nommKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = nommKeyid;
	}

	public String getNommType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setNommType(String nommType) {
		saveArray[ tableFldConstants.type.ordinal() ] = nommType;
	}

	public String getNommProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setNommProgKeyid(String nommProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = nommProgKeyid;
	}

	public String getNommBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setNommBachKeyid(String nommBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = nommBachKeyid;
	}

	public String getNommPreparedBy() {
		return (String) saveArray[ tableFldConstants.prepared_by.ordinal() ];
	}

	public void setNommPreparedBy(String nommPreparedBy) {
		saveArray[ tableFldConstants.prepared_by.ordinal() ] = nommPreparedBy;
	}

	public String getNommPreparedDate() {
		return (String) saveArray[ tableFldConstants.prepared_date.ordinal() ];
	}

	public void setNommPreparedDate(String nommPreparedDate) {
		saveArray[ tableFldConstants.prepared_date.ordinal() ] = nommPreparedDate;
	}

	public String getNommRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setNommRemarks(String nommRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = nommRemarks;
	}

	public String getNommTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setNommTempfield1(String nommTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = nommTempfield1;
	}

	public String getNommTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setNommTempfield2(String nommTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = nommTempfield2;
	}

	public String getNommTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setNommTempfield3(String nommTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = nommTempfield3;
	}

	public String getNommTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setNommTempfield4(String nommTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = nommTempfield4;
	}

	public String getNommTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setNommTempfield5(String nommTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = nommTempfield5;
	}

	public String getNommActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNommActive(String nommActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = nommActive;
	}

	public String getNommCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNommCreatedby(String nommCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = nommCreatedby;
	}

	public String getNommCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNommCreatedon(String nommCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = nommCreatedon;
	}

	public String getNommModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNommModifiedon(String nommModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = nommModifiedon;
	}

}

