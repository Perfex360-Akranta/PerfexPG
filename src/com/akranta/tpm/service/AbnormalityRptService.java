package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface AbnormalityRptService {
	public List<String []> getAllAbnormality(CommonFilter commonFilter, String type)  throws Exception;
	public List<String []> getAgeAbnormality(CommonFilter commonFilter)  throws Exception;
	public Workbook abnormalityReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat,String type) throws Exception;
	public Workbook abnormalityAgeReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public List<String[]> getAbnTagTrend(CommonFilter commonFilter)throws Exception;
	public Workbook getAbnTagTrendExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,
			String keyId, String columnId, String colIndexName) throws Exception;
	public Workbook AbnDetailsExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception;
	
	public List<String[]> getAbnSummaryGridData(CommonFilter commonFilter) throws Exception;	
	Workbook getAbnSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
	public void AbnormalityRptServiceImplJwt(String string);


}
