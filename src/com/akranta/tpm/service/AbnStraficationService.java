package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface AbnStraficationService {
	
	public List<String []> getAllAbnStrafication(CommonFilter commonFilter)  throws Exception;

	public Workbook AbnStartificationReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	/***/
	public List<String []> getAlStrafication(CommonFilter commonFilter)  throws Exception;
	
	public Workbook StartificationReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> getAllAbnStraficationType(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllAbnStraficationImpact(CommonFilter commonFilter) throws  Exception;
	public Workbook StartificationIdeVsComExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public void AbnStraficationServiceImplJwt(String string);
}
