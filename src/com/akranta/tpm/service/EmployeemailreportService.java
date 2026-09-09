package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EmpmailreportModel;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.google.gson.JsonObject;

public interface EmployeemailreportService{
public List<String[]> empreport(CommonFilter commonFilter,GridParams gridparam)throws Exception;
public Workbook getExcelreport(CommonFilter commonFilter,JSONObject jsonobj,String content)throws Exception;
public List<EmpmailreportModel> updateEmail(List<EmpmailreportModel> empMailEnableList)throws Exception;
}
