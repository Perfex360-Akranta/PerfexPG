package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.StdWorSheetkBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.StdTlStdworksheetmst;

public interface StandardizedWorkSheetService {
	public List<String[]> getStdWoShMainGrid() throws Exception;

	public List<String[]> getAllworkshtdtl(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllMaster(CommonFilter commonFilter) throws Exception;

	public StdTlStdworksheetmst create(StdTlStdworksheetmst newStdTlStdworksheetmst,StdTlStdworksheetmst existStdTlStdworksheetmst,StdWorSheetkBean stdWorSheetkBean) throws Exception;

	public StdTlStdworksheetmst update(
			StdTlStdworksheetmst newStdTlStdworksheetmst,
			StdTlStdworksheetmst existStdTlStdworksheetmst,
			StdWorSheetkBean stdWorSheetkBean) throws Exception;

	public StdTlStdworksheetmst selectmaster(String keyid) throws Exception;

	public StdTlStdworksheetmst delete(
			StdTlStdworksheetmst newStdTlStdworksheetmst,
			StdTlStdworksheetmst existStdTlStdworksheetmst,
			StdWorSheetkBean stdWorSheetkBean) throws  Exception;

	public Workbook getStdWorkExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

	public void DeleteStdRow(String keyid) throws Exception;

	public void DeleteStdWorkRow(
			List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl) throws Exception;

	public List<String[]> FillControlData(String keyid)throws Exception;
	public void StandardizedWorkSheetServiceImplJwt(String JwtToken);

	
}
