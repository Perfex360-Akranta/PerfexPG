package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ProductionLossDao {

	List<String[]> getAllproductionLoss(CommonFilter commonFilter) throws Exception;

	List<String[]> getAllproductionLossSubGrid(CommonFilter commonFilter) throws Exception;

	public Workbook getproductionLossExl(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat)throws Exception;

	List<String[]> getAllproductionLossforparatograph(
			CommonFilter chrtCommonFilter, String selectmonth)throws Exception;

	List<String[]> getdescorderlosses(String descsql)throws Exception;

	List<String[]> getAllproductionLossNewChart(CommonFilter chrtCommonFilter, String selmonth)throws Exception;

	List<String[]> getAllproductionLossDrill(CommonFilter commonFilter)throws Exception;
	
	List<String[]> getLossByFunctionalLocation(CommonFilter commonFilter)throws Exception;
	
	public Workbook getLossExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getLossAnalysisExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	List<String[]> getAllLossTimeDrill(CommonFilter commonFilter)throws Exception;
	public List<String[]> getAllLossTimeNewChart(CommonFilter chrtCommonFilter,String rowid)throws Exception;
	
	public void ProductionLossDaoImplJwt(String string);
	
	
}
