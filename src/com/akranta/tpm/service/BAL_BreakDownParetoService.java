package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_BreakDownParetoService {
	List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception;

	public Workbook paretoExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
}
