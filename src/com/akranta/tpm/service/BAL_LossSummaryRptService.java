package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_LossSummaryRptService {

	List<String[]> getAllloss(CommonFilter commonFilter) throws Exception;

	List<String[]> getAlllossdrill(CommonFilter commonFilter)throws Exception;

	Workbook LossSmryReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat,String mchId) throws Exception;

}
