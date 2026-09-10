package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlSparesmst;

public interface BAL_GenTlSparesmstDao {

	public abstract BAL_GenTlSparesmst create(BAL_GenTlSparesmst genTlSparesmst) throws Exception;
	public abstract BAL_GenTlSparesmst update(BAL_GenTlSparesmst genTlSparesmst) throws Exception;
	public abstract BAL_GenTlSparesmst delete(BAL_GenTlSparesmst genTlSparesmst) throws Exception;
	public BAL_GenTlSparesmst select(String sprField) throws Exception;
	public BAL_GenTlSparesmst selectSpares(String sprField) throws Exception;
	public List<String[]> getAllSpares(CommonFilter commonFilter) throws Exception;
	public List<String[]> recallSpares(String sprField) throws Exception;
	public abstract Workbook sparesMasterExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public abstract List<String[]> getAllSparesnew(CommonFilter commonFilter)throws Exception;
	public abstract BAL_GenTlSparesmst getFillValue(String keyId)throws Exception;
	

}

