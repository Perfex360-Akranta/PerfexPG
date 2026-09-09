package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface SuggestionNonJHEspReportService {

	List<String[]> getSuggestionSummaryGridData(CommonFilter commonFilter)throws Exception;

	Workbook getSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;
	
	public void SuggestionNonJHEspReportServiceImplJwt(String jwtToken);

}
