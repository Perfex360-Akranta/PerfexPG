package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface ImprovementVsCompletedDao {
	
	List<String[]> getAllImpVsComp(CommonFilter commonFilter)throws Exception;

	Workbook ImprovementVsCompleteExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;

	List<String[]> getAllIncedentImpVsComp(CommonFilter commonFilter)throws Exception;

	Workbook IncedentVsCompleteExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	
	List<String[]> getsuggestdimple(CommonFilter commonFilter) throws Exception;

	List<String[]> getchartforimpl(CommonFilter commonFilter) throws Exception;

	Workbook SuggestnVsImpl(CommonFilter commonFilter, JSONObject tableModel,
			String format) throws Exception, Exception;
	 List<String[]> getKznImplCount(CommonFilter commonFilter)throws Exception;
	 Workbook KznImplCountExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	 List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception;

	 Workbook KznSgnCountExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	 List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)throws Exception;	
	Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	List<String[]> getsuggchartforimpl(CommonFilter commonFilter) throws Exception;
	List<String[]> getKaizenCululative(CommonFilter commonFilter)throws Exception;
	//mano
	Workbook KaizenCumulativeExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;
    List<String[]> getEmpDmtKaizen(CommonFilter commonFilter)throws Exception;
	Workbook EmpDmtWiseKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public void ImprovementVsCompletedDaoImplJwt(String JwtToken);


}
