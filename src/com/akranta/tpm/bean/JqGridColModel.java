package com.akranta.tpm.bean;

public class JqGridColModel {
	
	
	private String index;
	private String name;
	private boolean editable;
	private boolean hidden;
	private int width;
	private String align;
	private String summaryType;
	private String summaryTpl;
	private String formatter;
	private String cellattr;
	private boolean key;
	private String headerRotation;	
	private String rowSpanIndex;
	private boolean pSave;
	private boolean pEditable;
	private boolean mandatory;
	private JqGridEditOptions pEditOptions; 
	private String[] formatterIndex;
	
	private boolean frozen;
	
	public boolean getFrozen() {
		return frozen;
	}
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int colWidth) {
		this.width = colWidth;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getSummaryType() {
		return summaryType;
	}
	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}
	public String getSummaryTpl() {
		return summaryTpl;
	}
	public void setSummaryTpl(String summaryTpl) {
		this.summaryTpl = summaryTpl;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getCellattr() {
		return cellattr;
	}
	public void setCellattr(String cellattr) {
		this.cellattr = cellattr;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getIndex() {
		return index;
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
	public void setHeaderRotation(String headerRotation) {
		this.headerRotation = headerRotation;
	}
	public String getHeaderRotation() {
		return headerRotation;
	}
	public String getRowSpanIndex() {
		return rowSpanIndex;
	}
	public void setRowSpanIndex(String rowSpanIndex) {
		this.rowSpanIndex = rowSpanIndex;
	}
	public String[] getFormatterIndex() {
		return formatterIndex;
	}
	public void setFormatterIndex(String[] formatterIndex) {
		this.formatterIndex = formatterIndex;
	}

	public boolean ispSave() {
		return pSave;
	}
	public void setpSave(boolean pSave) {
		this.pSave = pSave;
	}
	public boolean ispEditable() {
		return pEditable;
	}
	public void setpEditable(boolean pEditable) {
		this.pEditable = pEditable;
	}
	public JqGridEditOptions getpEditOptions() {
		return pEditOptions;
	}
	public void setpEditOptions(JqGridEditOptions pEditOptions) {
		this.pEditOptions = pEditOptions;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory (boolean mandatory) {
		this.mandatory = mandatory;
	}
	 


}
