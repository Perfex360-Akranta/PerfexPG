package com.akranta.tpm.model;

import java.util.List;

public class SapTlPerfexMappingMst {

	private String spmmKeyid		;	
	private String spmmProcessname	;
	private String spmmProcesscode	;
	private String spmmWebservicename	;
	private String spmmWebserviceuri	;
	private String spmmScenarioid		;
	private String spmmProcesstype	;
	private String spmmPrefix		;
	private String spmmTempfield2		;
	private String spmmTempfield3		;
	private String spmmTempfield4		;
	private String spmmTempfield5		;
	private String spmmActive			;
	private String spmmCreatedby		;
	private String spmmCreatedon		;
	private String spmmModifiedon		;
	
	private List<SapTlPerfexMappingDtl> sapTlPerfexMappingDtlList = null;
	
	
	public String getSpmmKeyid() {
		return spmmKeyid;
	}
	public void setSpmmKeyid(String spmmKeyid) {
		this.spmmKeyid = spmmKeyid;
	}
	public String getSpmmProcessname() {
		return spmmProcessname;
	}
	public void setSpmmProcessname(String spmmProcessname) {
		this.spmmProcessname = spmmProcessname;
	}
	public String getSpmmProcesscode() {
		return spmmProcesscode;
	}
	public void setSpmmProcesscode(String spmmProcesscode) {
		this.spmmProcesscode = spmmProcesscode;
	}
	public String getSpmmWebservicename() {
		return spmmWebservicename;
	}
	public void setSpmmWebservicename(String spmmWebservicename) {
		this.spmmWebservicename = spmmWebservicename;
	}
	public String getSpmmWebserviceuri() {
		return spmmWebserviceuri;
	}
	public void setSpmmWebserviceuri(String spmmWebserviceuri) {
		this.spmmWebserviceuri = spmmWebserviceuri;
	}
	public String getSpmmScenarioid() {
		return spmmScenarioid;
	}
	public void setSpmmScenarioid(String spmmScenarioid) {
		this.spmmScenarioid = spmmScenarioid;
	}
	public String getSpmmProcesstype() {
		return spmmProcesstype;
	}
	public void setSpmmProcesstype(String spmmProcesstype) {
		this.spmmProcesstype = spmmProcesstype;
	}
	public String getSpmmPrefix() {
		return spmmPrefix;
	}
	public void setSpmmPrefix(String spmmPrefix) {
		this.spmmPrefix = spmmPrefix;
	}
	public String getSpmmTempfield2() {
		return spmmTempfield2;
	}
	public void setSpmmTempfield2(String spmmTempfield2) {
		this.spmmTempfield2 = spmmTempfield2;
	}
	public String getSpmmTempfield3() {
		return spmmTempfield3;
	}
	public void setSpmmTempfield3(String spmmTempfield3) {
		this.spmmTempfield3 = spmmTempfield3;
	}
	public String getSpmmTempfield4() {
		return spmmTempfield4;
	}
	public void setSpmmTempfield4(String spmmTempfield4) {
		this.spmmTempfield4 = spmmTempfield4;
	}
	public String getSpmmTempfield5() {
		return spmmTempfield5;
	}
	public void setSpmmTempfield5(String spmmTempfield5) {
		this.spmmTempfield5 = spmmTempfield5;
	}
	public String getSpmmActive() {
		return spmmActive;
	}
	public void setSpmmActive(String spmmActive) {
		this.spmmActive = spmmActive;
	}
	public String getSpmmCreatedby() {
		return spmmCreatedby;
	}
	public void setSpmmCreatedby(String spmmCreatedby) {
		this.spmmCreatedby = spmmCreatedby;
	}
	public String getSpmmCreatedon() {
		return spmmCreatedon;
	}
	public void setSpmmCreatedon(String spmmCreatedon) {
		this.spmmCreatedon = spmmCreatedon;
	}
	public String getSpmmModifiedon() {
		return spmmModifiedon;
	}
	public void setSpmmModifiedon(String spmmModifiedon) {
		this.spmmModifiedon = spmmModifiedon;
	}
	

	public List<SapTlPerfexMappingDtl> getSapTlPerfexMappingDtlList() {
		return sapTlPerfexMappingDtlList;
	}
	public void setSapTlPerfexMappingDtlList(
			List<SapTlPerfexMappingDtl> sapTlPerfexMappingDtlList) {
		this.sapTlPerfexMappingDtlList = sapTlPerfexMappingDtlList;
	}
}
