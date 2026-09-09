package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface AppAccessReviewrptService{
	Workbook AppAccessReviewExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception;

	List<String[]> getAppAccessReview(CommonFilter commonFilter)throws Exception;

}
