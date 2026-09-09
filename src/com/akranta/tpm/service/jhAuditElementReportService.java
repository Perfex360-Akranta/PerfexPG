package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.upload.UploadException;

public interface jhAuditElementReportService {

	String populateTempTable(String excelFileName,JhaTlAuditreportmst jhaTlAuditreportmst) throws UploadException, Exception;

	List<String[]> getGriddata(CommonFilter commonFilter) throws Exception;

	List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception;

	JhaTlAuditreportmst selectData(String keyid)throws Exception;

	JhaTlAuditreportmst delete(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception;

	JhaTlAuditreportmst update(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception;

	List<JhaTlAuditreportdtl> delete(List<JhaTlAuditreportdtl> listJhaTlAuditreportdtl) throws Exception;

	Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

}
