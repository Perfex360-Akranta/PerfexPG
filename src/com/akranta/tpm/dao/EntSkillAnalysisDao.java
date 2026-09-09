package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.EntTlMultiskilldialymap;


public interface EntSkillAnalysisDao {
	List<String[]> getSkillGapAnalysis(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining) throws Exception;
	public Workbook getSkillGapAnalysisExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format,CommonFilterTraining commonFilterTraining) throws Exception;
	EntTlMultiskilldialymap create(
			List<EntTlMultiskilldialymap> lstEntTlMultiskilldialymap);

}
