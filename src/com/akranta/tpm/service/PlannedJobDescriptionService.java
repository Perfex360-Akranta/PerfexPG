package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PlannedJobDescBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlPlannedjobobservation;

public interface PlannedJobDescriptionService {

	SheTlPlannedjobobservation create(
			SheTlPlannedjobobservation newSheTlPlannedjobobservation,
			SheTlPlannedjobobservation existSheTlPlannedjobobservation,PlannedJobDescBean plannedJobDescBean) throws IOException,ValidationExceptions,BusinessApplicationExceptions, Exception;

	SheTlPlannedjobobservation update(
			SheTlPlannedjobobservation newSheTlPlannedjobobservation,
			SheTlPlannedjobobservation existSheTlPlannedjobobservation,PlannedJobDescBean plannedJobDescBean) throws IOException,ValidationExceptions,BusinessApplicationExceptions, Exception;

	SheTlPlannedjobobservation delete(
			SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception;

	List<String[]> getmaingrid(CommonFilter commonFilter) throws Exception;

	Workbook getPlJobMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

	SheTlPlannedjobobservation getSelectedData(String keyId) throws Exception;

	List<String[]> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;

	Workbook plannedJobExcel(String keyid, String format, String path,
			String imagePath, CommonFilter commonFilter) throws Exception;

	public List<String[]> getPojEmployee(String pjobKeyId)throws Exception;
	public String deleteDetail(String pjelPjobEmployeeid, String gridId)throws Exception,BusinessApplicationExceptions;
    public SheTlPlannedjobobservation UpdateActionPlanId(String pojkeyid,String aplmkeyid)throws Exception;
	public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public void PlannedJobDescriptionServiceImplJwt(String JwtToken);
	

}
