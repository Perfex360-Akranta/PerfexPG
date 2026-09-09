package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface UserAccessReviewService {

	public List<String[]> getUserAccessReviewRept(CommonFilter commonFilter) throws Exception;

	public Workbook UserAccessReviewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public abstract void UserAccessReviewServiceImplJwt(String jwtToken);
}
