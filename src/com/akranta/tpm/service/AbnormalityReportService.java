package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface AbnormalityReportService {

	public List<String[]> getAllAbnormalityGeneral(CommonFilter commonFilter) throws Exception;

	public Workbook abnormalityGeneralReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> getUnsafeDrillDn(CommonFilter commonFilter,String parentId) throws Exception;

	public List<String[]> getUnsafeMonth(CommonFilter commonFilter) throws Exception;

	//public List<String[]> getAllAbnormality();
	

}
