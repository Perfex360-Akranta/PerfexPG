package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.NoPlanSaveBean;
import com.akranta.tpm.model.CommonFilter;

public interface PcsComplianceRptService {

	List<String[]> getAllPcsRpt(CommonFilter commonFilter, String from)throws Exception;

	public Workbook pcscompExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format, String from)throws Exception;

	public String getShiftKeyid(String factId, String shiftCode) throws Exception;

	List<String[]> getNoPlanDuration(String mchId, String date, String shift)throws Exception;

	List<String[]> SaveNoPlan(List<NoPlanSaveBean> noPlanSaveList,
			String usrm_ccno)throws Exception;

	
}
