	package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_FailurePhnMatrixDao {

	List<String[]> getAllFailurePhnMatrix(CommonFilter commonFilter) throws Exception;

	public Workbook getppmatrix(CommonFilter commonFilter, JSONObject colmodel,	String rptFormat)throws Exception;
	
	 public abstract void BAL_FailurePhnMatrixDaoImplJwt(String jwtToken);
}
