package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_FailurePhnMatrixService {

	List<String[]> getAllFailurePhnMatrix(CommonFilter commonFilter)throws Exception;

	public Workbook ppmatrixExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	 public void BAL_FailurePhnMatrixServiceImplJwt(String JwtToken);

}
