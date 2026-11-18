package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_OeearprqrService {

	List<String[]> getAllOeearprqr(CommonFilter commonFilter)throws Exception;

	public Workbook oeeExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String format)throws Exception;

	List<String[]> getAllChartType(CommonFilter chrtCommonFilter)throws Exception;
	
	public void BAL_OeearprqrServiceImplJwt(String JwtToken);


}
