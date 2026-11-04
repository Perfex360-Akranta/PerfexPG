package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;


public interface BAL_ElapsedActivityService {
	
	public List<String[]> getAllElapsedMaintActivity(CommonFilter commonFilter) throws Exception;

	public Workbook elapsedMaintReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	public void BAL_ElapsedActivityServiceImplJwt(String JwtToken);


}