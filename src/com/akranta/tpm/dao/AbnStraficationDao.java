package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface AbnStraficationDao {
	
	public List<String []> getAbnStrafication(CommonFilter commonFilter) throws Exception ;

	public Workbook AbnStartificationReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
 
	/***/
	public List<String []> getAlStraficationDD(CommonFilter commonFilter) throws Exception ;

	public Workbook StartificationReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;

	public List<String[]> getAbnStraficationType(CommonFilter commonFilter)throws Exception;

	public List<String[]> getAbnStraficationImpact(CommonFilter commonFilter) throws Exception;
	public Workbook StartificationIdeVsComExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format)	throws Exception;

	public void AbnStraficationDaoImplJwt(String jwtToken);
}
