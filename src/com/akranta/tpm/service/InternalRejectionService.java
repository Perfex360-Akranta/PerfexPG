package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface InternalRejectionService {

	List<String[]> getCalender(CommonFilter commonFilter)throws Exception;

}
