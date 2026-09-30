package com.akranta.tpm.utils;

public class SAPMappingTblField {

	private String spmdPerfexColumnName	;
	private String spmdPerfexColumnType	;
	private String spmdPerfexColumnWidth;
	
	private String spmdPerfexRefTable;
	private String spmdPerfexRefColumn	;
	private String spmdPerfexRefMapcolumn;
		
	private String spmdSapTableName		;
	private String spmdSapColumnName	;
	private String spmdSapColumnType	;
	private String spmdSapColumnWidth	;
	private boolean spmdIncludeRequest  ;
	private boolean spmdIncludeResponse  ;
	private boolean spmdIncludeCondition  ;
	private String spmdConditionValue  ;
	private String spmdIsforalert			;
	private String spmdDisplayname ;
	
	public String getSpmdPerfexColumnName() {
		return spmdPerfexColumnName;
	}
	public void setSpmdPerfexColumnName(String spmdPerfexColumnName) {
		this.spmdPerfexColumnName = spmdPerfexColumnName;
	}
	public String getSpmdPerfexColumnType() {
		return spmdPerfexColumnType;
	}
	public void setSpmdPerfexColumnType(String spmdPerfexColumnType) {
		this.spmdPerfexColumnType = spmdPerfexColumnType;
	}
	public String getSpmdPerfexColumnWidth() {
		return spmdPerfexColumnWidth;
	}
	public void setSpmdPerfexColumnWidth(String spmdPerfexColumnWidth) {
		this.spmdPerfexColumnWidth = spmdPerfexColumnWidth;
	}
	public String getSpmdSapTableName() {
		return spmdSapTableName;
	}
	public void setSpmdSapTableName(String spmdSapTableName) {
		this.spmdSapTableName = spmdSapTableName;
	}
	public String getSpmdSapColumnName() {
		return spmdSapColumnName;
	}
	public void setSpmdSapColumnName(String spmdSapColumnName) {
		this.spmdSapColumnName = spmdSapColumnName;
	}
	public String getSpmdSapColumnType() {
		return spmdSapColumnType;
	}
	public void setSpmdSapColumnType(String spmdSapColumnType) {
		this.spmdSapColumnType = spmdSapColumnType;
	}
	public String getSpmdSapColumnWidth() {
		return spmdSapColumnWidth;
	}
	public void setSpmdSapColumnWidth(String spmdSapColumnWidth) {
		this.spmdSapColumnWidth = spmdSapColumnWidth;
	}
	public String getSpmdIsforalert() {
		return spmdIsforalert;
	}
	public void setSpmdIsforalert(String spmdIsforalert) {
		this.spmdIsforalert = spmdIsforalert;
	}
	public boolean isSpmdIncludeRequest() {
		return spmdIncludeRequest;
	}
	public void setSpmdIncludeRequest(boolean spmdIncludeRequest) {
		this.spmdIncludeRequest = spmdIncludeRequest;
	}
	public boolean isSpmdIncludeResponse() {
		return spmdIncludeResponse;
	}
	public void setSpmdIncludeResponse(boolean spmdIncludeResponse) {
		this.spmdIncludeResponse = spmdIncludeResponse;
	}
	public boolean isSpmdIncludeCondition() {
		return spmdIncludeCondition;
	}
	public void setSpmdIncludeCondition(boolean spmdIncludeCondition) {
		this.spmdIncludeCondition = spmdIncludeCondition;
	}
	public String getSpmdConditionValue() {
		return spmdConditionValue;
	}
	public void setSpmdConditionValue(String spmdConditionValue) {
		this.spmdConditionValue = spmdConditionValue;
	}

	public String getSpmdPerfexRefTable() {
		return spmdPerfexRefTable;
	}
	public void setSpmdPerfexRefTable(String spmdPerfexRefTable) {
		this.spmdPerfexRefTable = spmdPerfexRefTable;
	}
	public String getSpmdPerfexRefColumn() {
		return spmdPerfexRefColumn;
	}
	public void setSpmdPerfexRefColumn(String spmdPerfexRefColumn) {
		this.spmdPerfexRefColumn = spmdPerfexRefColumn;
	}
	public String getSpmdPerfexRefMapcolumn() {
		return spmdPerfexRefMapcolumn;
	}
	public void setSpmdPerfexRefMapcolumn(String spmdPerfexRefMapcolumn) {
		this.spmdPerfexRefMapcolumn = spmdPerfexRefMapcolumn;
	}
	public void setSpmdDisplayname(String spmdDisplayname) {
		this.spmdDisplayname = spmdDisplayname;
	}
	public String getSpmdDisplayname() {
		return spmdDisplayname;
	}

}
