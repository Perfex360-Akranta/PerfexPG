package com.akranta.tpm.utils;

import java.util.List;

public class SAPMappingMst {
	private List<SAPMappingTbl> sapMappingDtls;
	private List<SAPMappingTbl> sapMappingRetDtls;
	
	private String spmmProcessname	;
	private String spmmProcesscode	;
	private String spmmWebservicename	;
	private String spmmWebserviceuri	;
	private String spmmScenarioid		;
	private String spmmProcesstype	;
	private String spmmPrefix;

	public List<SAPMappingTbl> getSapMappingDtls() {
		return sapMappingDtls;
	}
	public void setSapMappingDtls(List<SAPMappingTbl> sapMappingDtls) {
		this.sapMappingDtls = sapMappingDtls;
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
	/**
	 * @param spmmPrifix the spmmPrifix to set
	 */
	public void setSpmmPrefix(String spmmPrefix) {
		this.spmmPrefix = spmmPrefix;
	}
	/**
	 * @return the spmmPrifix
	 */
	public String getSpmmPrefix() {
		return spmmPrefix;
	}
	/**
	 * @param sapMappingRetDtls the sapMappingRetDtls to set
	 */
	public void setSapMappingRetDtls(List<SAPMappingTbl> sapMappingRetDtls) {
		this.sapMappingRetDtls = sapMappingRetDtls;
	}
	/**
	 * @return the sapMappingRetDtls
	 */
	public List<SAPMappingTbl> getSapMappingRetDtls() {
		return sapMappingRetDtls;
	}
	
}
