package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ImprvmntSmryService {
	
	public List<String []> getAllImprovement(CommonFilter commonFilter) throws Exception;
	
	public List<String []> getAllImprovementSubGrp(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllImprovementSubGrpLoss(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllImprovementSubGrpPiller(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllImprovementSubGrpEqp(CommonFilter commonFilter)throws Exception;

	public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format) throws Exception;

	public Workbook improvementSmrySubReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public Workbook improvementSmrySubLossReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public Workbook improvementSmrySubPillerReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public Workbook improvementSmrySubEquipReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	

	public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception;

	
	public void ImprvmntSmryServiceImplJwt(String JwtToken);
}
