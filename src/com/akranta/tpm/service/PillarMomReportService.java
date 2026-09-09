package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface PillarMomReportService {

	Workbook getPillarMomAttdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format);

	List<String[]> getPillarMomAttd(CommonFilter commonFilter);
	
	public void PillarMomReportServiceImplJwt(String JwtToken);

}
