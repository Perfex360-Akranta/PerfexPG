package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.model.CommonFilter;

public interface UserAccessReviewDao {

	public List<String[]> getUserAccessReviewRept(CommonFilter commonFilter) throws Exception;

	public Workbook getUserAccessReviewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public abstract void UserAccessReviewDaoImplJwt(String jwtToken);
}
