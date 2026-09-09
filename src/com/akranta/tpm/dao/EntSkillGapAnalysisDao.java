package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;

public interface EntSkillGapAnalysisDao {
	public abstract List<String[]> getSkillGapGrid(CommonFilter commonFilter)throws Exception;

	public abstract Workbook getSkillGapExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
	public List<String[]> getEmpAssesmentTopicRatings(String assessmentId, String fromSpoke ) throws NoDataFoundException, Exception;

	public abstract List<String[]> getRadarChart(String key, String date, String empId) throws Exception;

	
}
