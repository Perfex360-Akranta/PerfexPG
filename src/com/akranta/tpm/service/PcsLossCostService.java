package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlLctcostperminute;

public interface PcsLossCostService {

	List<String[]> getPcsLossCost(CommonFilter commonFilter)throws Exception;

	Workbook getPcsLossCostExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format)throws Exception;

	void create(
			List<PcsTlLctcostperminute> lossCstList, String usrm_ccno)throws Exception;

	PcsTlLctcostperminute create(
			PcsTlLctcostperminute newPcsTlLctcostperminute,
			PcsTlLctcostperminute existPcsTlLctcostperminute)throws Exception ;

	PcsTlLctcostperminute update(
			PcsTlLctcostperminute newPcsTlLctcostperminute,
			PcsTlLctcostperminute existPcsTlLctcostperminute) throws Exception;

	List<String[]> getPcsLoss(CommonFilter commonFilter) throws Exception;

}
