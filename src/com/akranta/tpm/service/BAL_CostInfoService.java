package com.akranta.tpm.service;


import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;



public interface BAL_CostInfoService {

	public List<String[]> getAllCostInfo(CommonFilter commonFilter) throws Exception;

	public List<ComboBox> getBD(String condSql) throws Exception;
	
	public List<ComboBox> getPhenomena(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCause(String condSql,ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getEmpgrade(String condSql,ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getVendor(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getContractor(String condSql,ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getSpares(String condSql, ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getService(String condSql,ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getUtilities(String condSql,ComboFilter comboFilter) throws Exception;

	public List<ComboBox> getExpense(String condSql,ComboFilter comboFilter) throws Exception;

	
}



