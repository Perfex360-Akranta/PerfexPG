package com.akranta.tpm.utils;

import java.util.List;

public class SAPMappingTbl {
	
	private String spmdPerfexTableName	;
	private String spmdSapTableName	;
	
	private List<SAPMappingTblField> fieldDtls;

	
	public String getSpmdPerfexTableName() {
		return spmdPerfexTableName;
	}
	public void setSpmdPerfexTableName(String spmdPerfexTableName) {
		this.spmdPerfexTableName = spmdPerfexTableName;
	}
	public List<SAPMappingTblField> getFieldDtls() {
		return fieldDtls;
	}
	public void setFieldDtls(List<SAPMappingTblField> fieldDtls) {
		this.fieldDtls = fieldDtls;
	}
	/**
	 * @param spmdSapTableName the spmdSapTableName to set
	 */
	public void setSpmdSapTableName(String spmdSapTableName) {
		this.spmdSapTableName = spmdSapTableName;
	}
	/**
	 * @return the spmdSapTableName
	 */
	public String getSpmdSapTableName() {
		return spmdSapTableName;
	}

	
}
