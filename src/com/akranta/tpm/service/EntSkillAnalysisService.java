package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;

public interface EntSkillAnalysisService 
{
	List<String[]> getSkillGapAnalysis(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining) throws Exception;
	public Workbook getSkillGapAnalysisExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format,CommonFilterTraining commonFilterTraining) throws Exception;
}
