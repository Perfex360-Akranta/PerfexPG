package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonemst;

public interface WwblaRptService {
	
	public List<String[]> getAllWwblaGrid(CommonFilter commonFilter) throws Exception;
	public Workbook getWwblaExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
	//public BdmTlWwblamst getFillControl(String fishboneKeyId)throws Exception;
	public List<String[]> getWwblaDetail(String keyid)throws Exception;
	public Workbook getWwblaDetailForExcel(String keyid, JSONObject colModel, String format)throws Exception;

}
