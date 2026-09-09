package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.NoPlanSaveBean;
import com.akranta.tpm.model.CommonFilter;

public interface PcsComplianceRptDao {

	List<String[]> getpcsRpt(CommonFilter commonFilter, String from)throws Exception;

   public 	Workbook getpcscompExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat, String from)throws Exception;

   public String getShiftKeyid(String factId, String shiftCode)throws Exception;

List<String[]> getNoPlan(String mchId, String date, String shift)throws Exception;

List<String[]> NoPlanSave(List<NoPlanSaveBean> noPlanSaveList, String usrm_ccno)throws Exception;

}
