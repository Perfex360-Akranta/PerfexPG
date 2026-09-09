package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.StdTlStdworksheetmst;

public interface StandardizedWorkSheetDao {
	public abstract List<String[]> getStdWoShMainGrid() throws Exception;

	public abstract List<String[]> getAllworkshtdtl(CommonFilter commonFilter) throws Exception;

	public abstract List<String[]> getAllMaster(CommonFilter commonFilter)  throws Exception;

	public abstract StdTlStdworksheetmst create(StdTlStdworksheetmst newStdTlStdworksheetmst)throws Exception;

	public abstract StdTlStdworksheetmst selectmaster(String keyid) throws Exception;

	public abstract StdTlStdworksheetmst update(
			StdTlStdworksheetmst newStdTlStdworksheetmst) throws Exception;

	public abstract StdTlStdworksheetmst delete(
			StdTlStdworksheetmst newStdTlStdworksheetmst) throws Exception;

	public abstract Workbook getStdWorkExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception;

	public abstract void DeleteStdRow(String keyid) throws Exception;

	public abstract void DeleteStdWorkRow(
			List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl) throws Exception;

	public abstract List<String[]> FillControlData(String keyid)throws Exception;
	public abstract void StandardizedWorkSheetDaoImplJwt(String jwtToken);

}