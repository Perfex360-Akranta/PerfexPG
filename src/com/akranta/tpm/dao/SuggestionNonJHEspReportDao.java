package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface SuggestionNonJHEspReportDao {

	public Workbook getSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;

	public List<String[]> getSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception;
	
	public void SuggestionNonJHEspReportDaoImplJwt(String jwtToken);

}
