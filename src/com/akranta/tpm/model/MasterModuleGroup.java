package com.akranta.tpm.model;

public class MasterModuleGroup {
	
	
	private String isMMC;
	private String module;
	private String menuRights;
	private String tableName;
	private int numberRecords;
	private String lastModidied;
	private boolean isMenuFlag;
	private String menuCaption;
	private String menuNumber;
	private String menuName;
	private String menuFormName;
	private String loadFormArgument;
	private String mastIntegSql;
	private String mastIntegorderbySql;
	
	public static final String TBL_TABLENAME = "GEN_TL_MASTERMODULEGROUP";
	
	
	public void setMenuNumber(String menuNumber) {
		this.menuNumber = menuNumber;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName.replace("&", "");
	}
	public String getMenuFormName() {
		return menuFormName;
	}
	public String getMenuCaption() {
		return menuCaption;
	}
	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}
	public String getMenuNumber() {
		return menuNumber;
	}
	public void setMenuFormName(String menuFormName) {
		this.menuFormName = menuFormName;
	}
	public String getIsMMC() {
		return isMMC;
	}
	public void setIsMMC(String isMMC) {
		this.isMMC = isMMC;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMenuRights() {
		return menuRights;
	}
	public void setMenuRights(String menuRights) {
		this.menuRights = menuRights;
	}
	public String getLastModidied() {
		return lastModidied;
	}
	public void setLastModidied(String lastModidied) {
		this.lastModidied = lastModidied;
	}
	public boolean isMenuFlag() {
		return isMenuFlag;
	}
	public void setMenuFlag(boolean isMenuFlag) {
		this.isMenuFlag = isMenuFlag;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @param numberRecords the numberRecords to set
	 */
	public void setNumberRecords(int numberRecords) {
		this.numberRecords = numberRecords;
	}
	/**
	 * @return the numberRecords
	 */
	public int getNumberRecords() {
		return numberRecords;
	}

	public String getLoadFormArgument() {
		return loadFormArgument;
	}
	public void setLoadFormArgument(String loadFormArgument) {
		this.loadFormArgument = loadFormArgument;
	}
	public String getMastIntegSql() {
		return mastIntegSql;
	}
	public void setMastIntegSql(String mastIntegSql) {
		this.mastIntegSql = mastIntegSql;
	}
	public String getMastIntegorderbySql() {
		return mastIntegorderbySql;
	}
	public void setMastIntegorderbySql(String mastIntegorderbySql) {
		this.mastIntegorderbySql = mastIntegorderbySql;
	}
}
