package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ProductionLossSummaryDao {

	List<String[]> getProductionLossSummaryRpt(CommonFilter commonFilter)throws Exception;

	Workbook getProdLossSmryExcel(CommonFilter commonFilter,JSONObject colModel, String format)throws Exception;
}
