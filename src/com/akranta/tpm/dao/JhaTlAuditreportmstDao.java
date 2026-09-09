package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.upload.UploadException;

public interface JhaTlAuditreportmstDao {

	public abstract JhaTlAuditreportmst create(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception;
	public abstract JhaTlAuditreportmst update(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception;
	public abstract JhaTlAuditreportmst delete(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception;
	public abstract String populateTempTable(String excelFileName,
			JhaTlAuditreportmst jhaTlAuditreportmst) throws UploadException, Exception;
	public abstract List<String[]> getGriddata(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract JhaTlAuditreportmst selectData(String keyid)throws Exception;
	public abstract List<JhaTlAuditreportdtl> delete(List<JhaTlAuditreportdtl> listJhaTlAuditreportdtl) throws Exception;
	public abstract Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

}

