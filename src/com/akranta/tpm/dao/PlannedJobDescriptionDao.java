package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlPlannedjobobservation;

public interface PlannedJobDescriptionDao {
	public abstract SheTlPlannedjobobservation create(SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception;
	public abstract SheTlPlannedjobobservation update(SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception;
	public abstract SheTlPlannedjobobservation delete(SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception;
	public abstract List<String[]> getmaingrid(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getPlJobMainExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws  Exception;
	public abstract String getElementID(String pjobFlid) throws Exception;
	public abstract SheTlPlannedjobobservation getSelectedData(String keyid) throws Exception;
	public abstract List<String[]> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;
	public abstract Map<Integer, List<String[]>> PlanExcel(String keyid,
			String format, CommonFilter commonFilter)throws Exception;

	public List<String[]> getPojEmployee(String pjobKeyId)throws Exception;
	public String deleteDetail(String pjelPjobEmployeeid, String gridId)throws Exception,BusinessApplicationExceptions;
    public SheTlPlannedjobobservation UpdateActionPlanId(String pojkeyid,String aplmkeyid)throws Exception;
	public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter commonFilter) throws Exception;
	public abstract void PlannedJobDescriptionDaoImplJwt(String jwtToken);
		
		
	
}
