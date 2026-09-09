package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface PcsSmryRptDao {

	List<String[]> getAllPcsSummary(CommonFilter commonFilter)throws Exception;

	public Workbook getAllPcsSummaryExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat, String type)throws Exception;

	List<String[]> getpcsRemarks(CommonFilter commonFilter)throws Exception;

}
