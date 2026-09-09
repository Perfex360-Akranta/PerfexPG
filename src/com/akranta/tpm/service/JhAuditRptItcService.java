package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface JhAuditRptItcService {
	public List<String []> getJhAuditRpt(CommonFilter commonFilter)  throws Exception;
	
	public Workbook getAllJHAuditRptExl(String rowId, String format,	String path, String imagePath)throws Exception;
	
	
	public Workbook JhAuditReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

	
	public List<String[]> getHseAccTrendRptgraph(
			CommonFilter chrtCommonFilter, String keyId)throws Exception;



}

	

