package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.JqGridColModel;

public class GridColModel {
	
	private String formattorFromCol;
	private String formattorToCol;
	private String summaryType;
	private String summaryTpl;
	private String formatter;
	private String cellattr;
	private int headerNum;
	private List<String> multiformatter;
	private List<String> multiformattorFromCol;
	private List<String> multiformattorToCol;
	public GridColModel()
	{
		
	}
	
	public String getFormattorFromCol() {
		return formattorFromCol;
	}
	public void setFormattorFromCol(String formattorFromCol) {
		this.formattorFromCol = formattorFromCol;
	}
	public String getFormattorToCol() {
		return formattorToCol;
	}
	public void setFormattorToCol(String formattorToCol) {
		this.formattorToCol = formattorToCol;
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
	public int getHeaderNum() {
		return headerNum;
	}
	public void setHeaderNum(int headerNum) {
		this.headerNum = headerNum;
	}
	
	public void setMultiformatter(List<String> multiformatter) {
		this.multiformatter = multiformatter;
	}

	public List<String> getMultiformatter() {
		return multiformatter;
	}
	public void setMultiformattorFromCol(List<String> multiformattorFromCol) {
		this.multiformattorFromCol = multiformattorFromCol;
	}

	public List<String> getMultiformattorFromCol() {
		return multiformattorFromCol;
	}
	public void setMultiformattorToCol(List<String> multiformattorToCol) {
		this.multiformattorToCol = multiformattorToCol;
	}
	public List<String> getMultiformattorToCol() {
		return multiformattorToCol;
	}
}
