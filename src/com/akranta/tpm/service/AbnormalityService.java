package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface AbnormalityService {
	public List<String []> getAllAbnormality(CommonFilter commonFilter)  throws Exception;
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception;
	public List<String []> getAbnjh(CommonFilter commonFilter)  throws Exception;
	public List<String[]> getAbnjhgraph(CommonFilter commonFilter)throws Exception;
	public Workbook AbnormalityReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook AbnjhReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAbnIdentifiedCompleted(CommonFilter commonFilter)throws Exception;
	public Workbook AbnormalityIdentifiedExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public void AbnormalityServiceImplJwt(String string);
}

