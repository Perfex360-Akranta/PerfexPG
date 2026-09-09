package com.akranta.tpm.model;

public class ComboBox {
	
	private String id;
	private String text;
	private  String []  columns;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String [] getColumns() {
		return columns;
	}

	public void setColumns(String [] columns) {
		this.columns = columns;
	}
	

}
