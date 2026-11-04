package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface BAL_CBMTrendService {

	public List<String []> getAllCbmTrend(CommonFilter commonFilter) throws Exception;
	
	public void BAL_CBMTrendServiceImplJwt(String JwtToken);
	
	Workbook CBMExcelReport(CommonFilter commonFilter,JSONObject comName, String rptFormat) throws Exception;

}
