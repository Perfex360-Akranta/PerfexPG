package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface EntTrainingAreaDao {
	public abstract List<String[]> getTrainingAreaGrid(CommonFilter commonFilter, String training)throws Exception;

	public abstract Workbook getTrainingAreaExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;

}
