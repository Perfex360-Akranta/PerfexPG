package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;

import net.sf.json.JSONObject;

public interface SkillIndexGridEntryService {

	List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception;

	public EntTlSkillindexassessmst createSkillAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl,String usrm_keyid) throws Exception;

	List<String[]> getTopicTaskModify(CommonFilter commonFilter) throws Exception;

	List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) throws Exception;

	
	public EntTlSkillindexassessmst updateSkillAssement(
			List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl,
			String usrm_keyid)throws Exception;

	List<String[]> getEmpList(CommonFilter commonFilter)throws Exception;

	Workbook getExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	
	public Workbook multipleSkillIndexReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	
	public void SkillIndexGridEntryServiceImplJwt(String JwtToken);
	
	public List<String[]> getEmpListFunction(CommonFilter commonfilter,GridParams gridParams) throws Exception;


}
