package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface EntRptTrainingLogDao 
{
	List<String[]> getRptTrainingLog(CommonFilter commonFilter) throws Exception;

	Workbook getTainingLogRpt(CommonFilter commonFilter, JSONObject colmodel,
			String rptFormat)throws Exception;
}
