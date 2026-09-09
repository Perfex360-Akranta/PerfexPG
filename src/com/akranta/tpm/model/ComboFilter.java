package com.akranta.tpm.model;

import java.util.List;

import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;

public class ComboFilter  {
	
	private String id = null;
	private String code = null;
	private String name = null;
	private String idField = null;
	private String codeField= null;
	private String nameField = null;
	
	private String condSql;
	private String tableName;
	private String orderByField;
    /**Added By Manikandan**/
	private String mode;
	private String page;
	private String rows;
	private GridParams gridparam;
	private String otherselectitems;
	private String newSelectQuery;
	private String isGetCol;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOrderByField() {
		return orderByField;
	}
	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getCodeField() {
		return codeField;
	}
	public void setCodeField(String codeField) {
		this.codeField = codeField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param condSql the condSql to set
	 */
	public void setCondSql(String condSql) {
		this.condSql = condSql;
	}
	/**
	 * @return the condSql
	 */
	public String getCondSql() {
		return condSql;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPage() {
		return page;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getRows() {
		return rows;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public GridParams getGridparam() {
		return gridparam;
	}
	public void setGridparam(GridParams gridparam) {
		this.gridparam = gridparam;
	}
	public String getOtherselectitems() {
		return otherselectitems;
	}
	public void setOtherselectitems(String otherselectitems) {
		this.otherselectitems = otherselectitems;
	}
	public String getNewSelectQuery() {
		return newSelectQuery;
	}
	public void setNewSelectQuery(String newSelectQuery) {
		this.newSelectQuery = newSelectQuery;
	}
	public String getIsGetCol() {
		return isGetCol;
	}
	public void setIsGetCol(String isGetCol) {
		this.isGetCol = isGetCol;
	}
	
	
	}
