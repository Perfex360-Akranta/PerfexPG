package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlLctcostperminute;

public interface PcsTlLctcostperminuteDao {

	public abstract void create(List<PcsTlLctcostperminute> retObject) throws Exception;
	public abstract PcsTlLctcostperminute update(PcsTlLctcostperminute pcsTlLctcostperminute) throws Exception;
	public abstract PcsTlLctcostperminute delete(PcsTlLctcostperminute pcsTlLctcostperminute) throws Exception;
	public abstract List<String[]> getPcsLossCost(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getPcsLossCostExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format)throws Exception;
	//public abstract PcsTlLctcostperminute create(PcsTlLctcostperminute newPcsTlLctcostperminute) throws Exception;
	//public abstract List<String[]> getPcsLoss(CommonFilter commonFilter) throws Exception;

}

