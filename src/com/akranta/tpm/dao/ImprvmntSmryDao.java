package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ImprvmntSmryDao {
	
	public List<String []> getAllImprovmntSmry(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllImprovementSubGrp(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllImprovementSubGrpLoss(CommonFilter commonFilter)throws Exception;

	public List<String[]> getAllImprovementSubGrpPiller(CommonFilter commonFilter)throws Exception;

	public List<String[]> getAllImprovementSubGrpEqp(CommonFilter commonFilter) throws Exception;

	public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	public Workbook improvementSmrySubReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;

	public Workbook improvementSmrySubLossReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;

	public Workbook improvementSmrySubPillerReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;

	public Workbook improvementSmrySubEquipReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;

	public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception;

	public void ImprvmntSmryDaoImplJwt(String JwtToken);
}
