package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
public interface EntRptTrainingLogService 
{
	List<String[]> getRptTrainingLog(CommonFilter commonFilter)throws Exception;

	Workbook entRptTrainingLogExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;
	
}
