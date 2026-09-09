package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

import net.sf.json.JSONObject;

public interface NewCustomerComplaintService {
    public List<String[]> custComList()throws Exception;
    public List<String[]> customerList(String Keyid) throws Exception;
	public Workbook getCustomerComplaintExportToExcel(JSONObject colmodel,String format,
			CommonFilter commonFilter1)throws Exception;
}
