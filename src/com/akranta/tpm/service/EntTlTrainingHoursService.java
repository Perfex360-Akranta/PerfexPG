package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface EntTlTrainingHoursService 
{

	List<String[]> getTrainingHoursPgm(CommonFilter commonFilter) throws Exception;
	
	List<String[]> getTrainingHoursPgmGraph(CommonFilter commonFilter) throws Exception;
	
	public Workbook getTrainingHoursExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	
	
}
