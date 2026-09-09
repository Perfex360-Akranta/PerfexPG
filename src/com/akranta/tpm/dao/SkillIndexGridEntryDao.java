package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;

import net.sf.json.JSONObject;

public interface SkillIndexGridEntryDao {

	List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception;

	EntTlSkillindexassessmst createAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) throws Exception;

	List<String[]> getTopicTaskModify(CommonFilter commonFilter) throws Exception;

	List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName);

	EntTlSkillindexassessmst updateSkillAssement(
			List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl)  throws Exception;

	List<String[]> getEmpList(CommonFilter commonfilter) throws Exception;

	Workbook getExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	
	Workbook multipleSkillIndexReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	
	public void SkillIndexGridEntryDaoImplJwt(String JwtToken); 
	
	public List<String[]> getEmpListFunction(CommonFilter commonfilter,GridParams gridParams) throws Exception;

}
