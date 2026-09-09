package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface AppAccessReviewrptDao {

	List<String[]> getAppAccessReview(CommonFilter commonFilter) throws Exception;

	
	Workbook AppAccessReviewReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception;

}
