package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonemst;

public interface WwblaRptDao {
	
	public abstract List<String[]> getAllWwblaGrid(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getWwblaExcel(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getWwblaDetail(String keyid)throws Exception;
	//public abstract BdmTlWwblamst getFillControl(String wwblaKeyId)throws Exception;
	public abstract Workbook getWwblaDetailForExcel(String keyid,JSONObject tblJSONObj,String format)throws Exception;

}
