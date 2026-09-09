/*  Author ManiKandan*/
package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ImprovementProjSheetKaiDao {
	public List<String[]> getImprovementProjSheetkaiReport(CommonFilter commonFilter)
	throws Exception;
	//public List<String[]> getImprovementProjSheetkaiexlReport(String kaizId) throws Exception;
	
	public Map<Integer, List<String[]>> getImprovementProjSheetkaiexlReport(String kaizId, String flid, String benTypeVal, String user,String format) throws Exception;

	public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

//	public List<String[]> getImprovementProjSheetkaiexlReportApproval(
	//		String User, String flid, String benTypeVal, String kaizId)throws Exception;

	public List<String[]> getImprovementProjSheetkaiexlReportHDData(String kaizId)throws Exception;

	public List<String[]> getteamdata(String flid, String kaizId)throws Exception;

	public List<String[]> getactivitypillar(String kaizId)throws Exception;

	public List<String[]> getImprovementProjSheetkaiexlReportApproval(
			String user, String flid, String benTypeVal, String kaizId)throws Exception;

}
