package com.akranta.tpm.upload;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class UploadException extends Exception {

	private Workbook uploadExcel = null;
	private Sheet sheet =null;
	private int errorNumber;
	
	public UploadException(Workbook uploadExcel,int errorNumber) {
		this.uploadExcel = uploadExcel;
		this.errorNumber = errorNumber;
		// TODO Auto-generated constructor stub
	}
	
	public UploadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public UploadException(int errorNumber,String message ){
		super(message);
		this.errorNumber = errorNumber;
	}		
	public UploadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UploadException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public void setUploadExcel(Workbook uploadExcel) {
		this.uploadExcel = uploadExcel;
	}

	public Workbook getUploadExcel() {
		return uploadExcel;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

}
