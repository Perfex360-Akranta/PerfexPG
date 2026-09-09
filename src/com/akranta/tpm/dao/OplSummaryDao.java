package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface OplSummaryDao {

	public List<String []> getOplSummaryDao(CommonFilter commonFilter) throws Exception;
	public Workbook getOPLSummaryExcel(CommonFilter commonFilter,JSONObject colModel,String format) throws Exception;
}
