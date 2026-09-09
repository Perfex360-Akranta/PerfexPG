package com.akranta.tpm.upload;

public class ExcelField {
	String name;
	int excelColIndex;
	int tmpTblColIndex;
	boolean isNoReference;
	int dataType;
	
	public ExcelField(String name, int excelColIndex,int tmpTblColIndex,boolean isNoReference, int dataType ){
		this.name = name;
		this.excelColIndex = excelColIndex;
		this.tmpTblColIndex = tmpTblColIndex;
		this.isNoReference = isNoReference;
		this.dataType = dataType;
	}
}
