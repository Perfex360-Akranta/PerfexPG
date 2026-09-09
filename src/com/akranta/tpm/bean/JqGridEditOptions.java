package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.model.ComboBox;

public class JqGridEditOptions {
	
	private String editType = null;
	private Integer maxLength = null;
	private boolean isNumber = false;
	private Integer rows = null;
	private Integer cols = null;
	private String dateFormat = null;
	private boolean multiple = false;
	private String url = null;
	private boolean isLocal = false;
	private Integer panelHeight = null;
	private Integer panelWidth = null;
	private String idColName = null;
	private String caption = null;
	private List<ComboBox> options = null;
	
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public boolean isNumber() {
		return isNumber;
	}
	public void setNumber(boolean isNumber) {
		this.isNumber = isNumber;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getCols() {
		return cols;
	}
	public void setCols(Integer cols) {
		this.cols = cols;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isLocal() {
		return isLocal;
	}
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	public Integer getPanelHeight() {
		return panelHeight;
	}
	public void setPanelHeight(Integer panelHeight) {
		this.panelHeight = panelHeight;
	}
	public Integer getPanelWidth() {
		return panelWidth;
	}
	public void setPanelWidth(Integer panelWidth) {
		this.panelWidth = panelWidth;
	}
	public String getIdColName() {
		return idColName;
	}
	public void setIdColName(String idColName) {
		this.idColName = idColName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public List<ComboBox> getOptions() {
		return options;
	}
	public void setOptions(List<ComboBox> options) {
		this.options = options;
	}
	
	

}
