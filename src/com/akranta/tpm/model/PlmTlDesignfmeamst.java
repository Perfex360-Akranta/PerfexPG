package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlDesignfmeamst {

	private  Object [] saveArray = null;  
	
	List<PlmTlDesignfmeadtl> plmTlDesignfmeadtl;

	public enum   tableFldConstants
	{	keyid, flid, date, no, systemid, supsystemid, componentid, preparedby
		, coreteam, doctype, docmstid, docdtlsid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public PlmTlDesignfmeamst()
	{
		setPlmTlDesignfmeadtl(new ArrayList());
		saveArray = new  Object [ 21 ];
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmdmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmdmKeyid(String fmdmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmdmKeyid;
	}

	public String getFmdmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFmdmFlid(String fmdmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = fmdmFlid;
	}

	public String getFmdmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setFmdmDate(String fmdmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = fmdmDate;
	}

	public String getFmdmNo() {
		return (String) saveArray[ tableFldConstants.no.ordinal() ];
	}

	public void setFmdmNo(String fmdmNo) {
		saveArray[ tableFldConstants.no.ordinal() ] = fmdmNo;
	}

	public String getFmdmSystemid() {
		return (String) saveArray[ tableFldConstants.systemid.ordinal() ];
	}

	public void setFmdmSystemid(String fmdmSystemid) {
		saveArray[ tableFldConstants.systemid.ordinal() ] = fmdmSystemid;
	}

	public String getFmdmSupsystemid() {
		return (String) saveArray[ tableFldConstants.supsystemid.ordinal() ];
	}

	public void setFmdmSupsystemid(String fmdmSupsystemid) {
		saveArray[ tableFldConstants.supsystemid.ordinal() ] = fmdmSupsystemid;
	}

	public String getFmdmComponentid() {
		return (String) saveArray[ tableFldConstants.componentid.ordinal() ];
	}

	public void setFmdmComponentid(String fmdmComponentid) {
		saveArray[ tableFldConstants.componentid.ordinal() ] = fmdmComponentid;
	}

	public String getFmdmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setFmdmPreparedby(String fmdmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = fmdmPreparedby;
	}

	public String getFmdmCoreteam() {
		return (String) saveArray[ tableFldConstants.coreteam.ordinal() ];
	}

	public void setFmdmCoreteam(String fmdmCoreteam) {
		saveArray[ tableFldConstants.coreteam.ordinal() ] = fmdmCoreteam;
	}
	
	public String getFmdmDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setFmdmDoctype(String fmdmDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = fmdmDoctype;
	}

	public String getFmdmDocmstid() {
		return (String) saveArray[ tableFldConstants.docmstid.ordinal() ];
	}

	public void setFmdmDocmstid(String fmdmDocmstid) {
		saveArray[ tableFldConstants.docmstid.ordinal() ] = fmdmDocmstid;
	}

	public String getFmdmDocdtlsid() {
		return (String) saveArray[ tableFldConstants.docdtlsid.ordinal() ];
	}

	public void setFmdmDocdtlsid(String fmdmDocdtlsid) {
		saveArray[ tableFldConstants.docdtlsid.ordinal() ] = fmdmDocdtlsid;
	}

	public String getFmdmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmdmTempfield1(String fmdmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmdmTempfield1;
	}

	public String getFmdmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmdmTempfield2(String fmdmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmdmTempfield2;
	}

	public String getFmdmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmdmTempfield3(String fmdmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmdmTempfield3;
	}

	public String getFmdmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmdmTempfield4(String fmdmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmdmTempfield4;
	}

	public String getFmdmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmdmTempfield5(String fmdmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmdmTempfield5;
	}

	public String getFmdmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmdmActive(String fmdmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmdmActive;
	}

	public String getFmdmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmdmCreatedby(String fmdmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmdmCreatedby;
	}

	public String getFmdmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmdmCreatedon(String fmdmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmdmCreatedon;
	}

	public String getFmdmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmdmModifiedon(String fmdmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmdmModifiedon;
	}

	public void setPlmTlDesignfmeadtl(List<PlmTlDesignfmeadtl> plmTlDesignfmeadtl) {
		this.plmTlDesignfmeadtl = plmTlDesignfmeadtl;
	}

	public List<PlmTlDesignfmeadtl> getplmTlDesignfmeadtl() {
		return plmTlDesignfmeadtl;
	}

}

