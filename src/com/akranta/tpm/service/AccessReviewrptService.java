package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface AccessReviewrptService {

	List<String[]> getAccessReview(CommonFilter commonFilter) throws Exception;


	Workbook getAccessReviewrptExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
}
