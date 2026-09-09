package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface EntTrainingAreaService {
	public abstract List<String[]> getTrainingAreaGrid(CommonFilter commonFilter, String training)throws Exception;

	public abstract Workbook TrainingAreaExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;

}
