package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface PcsSmryRptService {

	List<String[]> getAllPcsSummary(CommonFilter commonFilter) throws Exception;

	public Workbook pcsSmryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format, String type)throws Exception;

	List<String[]> getpcsRemarks(CommonFilter commonFilter)throws Exception;

}
