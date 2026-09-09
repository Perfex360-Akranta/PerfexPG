package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface OplSummaryService {

	public List<String []> getAllOPLSummaryService(CommonFilter commonFilter) throws Exception;
	public Workbook getOPLSummaryExcel(CommonFilter commonFilter,JSONObject colModel,String format) throws Exception;
}
