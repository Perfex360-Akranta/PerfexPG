package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;

public interface EntSkillGapAnalysisService {

	public abstract List<String[]> getSkillGapAnalysisGrid(CommonFilter commonFilter)throws Exception;

	
	public List<String[]> getEmpAssesmentTopicRatings(String assessmentId, String fromSpoke ) throws NoDataFoundException, Exception;
	public abstract Workbook SkillGapExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;


	public abstract List<String[]> getRadarChartData(String key, String date, String empId)throws Exception;
}
